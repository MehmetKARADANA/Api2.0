package main

import (
    "context"
	"fmt"
	"net/http"
	"strings"
	"encoding/json"  // JSON işleme
	"io/ioutil"      // Dosya okuma/yazma işlemleri
	"log"            // Loglama işlemleri
	"os"             // Dosya ve ortam değişkenleri için
	"sync"           // Goroutine senkronizasyonu
	"gopkg.in/yaml.v2"  // YAML işlemleri için (bu paket dış kütüphane)
	"github.com/gin-gonic/gin"
)


const (
    yamlFilePath       = "servers.yaml"
)


var (
    ctx         = context.TODO()
    serverConfig ServerConfig
)

type Agency struct {
    Name       string `yaml:"name"`
    URL        string `yaml:"url"`
    DistanceKM int    `yaml:"distance_km"`
}

type ServerConfig struct {
    Agencies []Agency `yaml:"servers"`
}


func init() {
    var err error

    file, err := os.Open(yamlFilePath)
    if err != nil {
        log.Fatalf("YAML file opening error: %v", err)
    }
    defer file.Close()

    decoder := yaml.NewDecoder(file)
    err = decoder.Decode(&serverConfig)
    if err != nil {
        log.Fatalf("YAML file reading error: %v", err)
    }
}


func fetchJSON(url string) (interface{}, error) {
    resp, err := http.Get(url)
    if err != nil {
        return nil, fmt.Errorf("HTTP request error: %v", err)
    }
    defer resp.Body.Close()

    body, err := ioutil.ReadAll(resp.Body)
    if err != nil {
        return nil, fmt.Errorf("Response reading error: %v", err)
    }
    log.Printf("Response: %s\n", body)

    var result interface{}
    if err := json.Unmarshal(body, &result); err != nil {
        return nil, fmt.Errorf("JSON parse error: %v", err)
    }
    return result, nil
}

func validateToken(c *gin.Context) bool {
	authHeader := c.GetHeader("Authorization")

	// Token gönderilmediyse hata döndür
	if authHeader == "" {
		c.JSON(http.StatusUnauthorized, gin.H{"error": "Token is missing or not provided."})
		return false
	}

	// Token Bearer ile başlamıyorsa geçersiz say ve hata döndür
	if !strings.HasPrefix(authHeader, "Bearer ") {
		c.JSON(http.StatusUnauthorized, gin.H{"error": "invalid Token"})
		return false
	}

	// Bearer öneki olmadan token'ı çıkar
	token := strings.TrimPrefix(authHeader, "Bearer ")

	// Token'ı doğrulayan dış servise istek gönder ve sonucu kontrol et
	isValid, statusCode, errMsg := validateTokenWithAuthService(token)
	if !isValid {
		c.JSON(statusCode, gin.H{"error": errMsg})
		return false
	}

	return true
}
func validateTokenWithAuthService(token string) (bool, int, string) {
	url := "http://host.docker.internal:8084/api/auth/authenticate"

	client := &http.Client{}
	req, err := http.NewRequest("GET", url, nil)
	if err != nil {
		fmt.Println("Request creation error:", err)
		return false, http.StatusInternalServerError, "Request creation error: An error occurred during token validation."
	}

	// Bearer token'ı header'a ekle
	req.Header.Add("Authorization", "Bearer "+token)

	resp, err := client.Do(req)
	if err != nil {
		fmt.Println("Token validation request error:", err)
		return false, http.StatusInternalServerError, "Token validation request error: An error occurred during token validation."
	}
	defer resp.Body.Close()

	if resp.StatusCode == http.StatusOK {
		return true, http.StatusOK, ""
	}

	return false, http.StatusUnauthorized, "Token invalid"
}
func getRooms(c *gin.Context) {
	// Token doğrulama
	if !validateToken(c) {
		return
	}

	var wg sync.WaitGroup
	results := make(chan map[string]interface{}, len(serverConfig.Agencies))

	// Paralel istekler gönderiliyor
	for _, agency := range serverConfig.Agencies {
		wg.Add(1)
		go func(agency Agency) {
			defer wg.Done()
			jsonData, err := fetchJSON(agency.URL + "/rooms")
			if err != nil {
				log.Printf("Error with agency %s: %v", agency.Name, err)
				results <- map[string]interface{}{
					"agency":      agency.Name,
					"distance_km": agency.DistanceKM,
					"error":       err.Error(), // Hata bilgisi gönderiliyor
				}
				return
			}
			results <- map[string]interface{}{
				"agency":      agency.Name,
				"distance_km": agency.DistanceKM,
				"data":        jsonData,
			}
		}(agency)
	}

	// Paralel işlemler tamamlandığında kanalı kapatıyoruz
	go func() {
		wg.Wait()
		close(results)
	}()

	// Tüm sonuçları topluyoruz
	var allResults []map[string]interface{}
	for result := range results {
		allResults = append(allResults, result)
	}

	// Yanıt olarak tüm sonuçları JSON formatında döndür
	c.JSON(http.StatusOK, allResults)
}

func main() {
	router := gin.Default()
	router.GET("/api/getRooms", getRooms)

	// Sunucuyu başlat
	router.Run(":8083")
}
