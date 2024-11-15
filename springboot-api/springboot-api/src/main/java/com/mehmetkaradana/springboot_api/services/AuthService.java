/*package com.mehmetkaradana.springboot_api.services;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {

    public boolean validateToken(HttpServletRequest request) {
       // System.out.println("geldi");
        String authHeader = request.getHeader("Authorization");
        String token = (authHeader != null && authHeader.startsWith("Bearer ")) ? authHeader.substring(7) : null;
       // System.out.println("Gelen Token: " + token);

        if (token == null || token.isEmpty()) {
          //  throw new IllegalArgumentException("Token eksik veya gönderilmedi");
            return true;
        }
        return !validateTokenWithAuthService(token);
    }

    private boolean validateTokenWithAuthService(String token) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8084/api/auth/authenticate";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            // Auth servisine istek
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return true;  // Geçerli token
            } else {
                System.out.println("invalid token: " + response.getStatusCode());
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
}*//*
package com.mehmetkaradana.springboot_api.services;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {

    public boolean validateToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = (authHeader != null && authHeader.startsWith("Bearer ")) ? authHeader.substring(7) : null;

        // Check if the token is missing or empty
        if (token == null || token.isEmpty()) {
            throw new IllegalArgumentException("Token is missing or not provided.");
        }
        return validateTokenWithAuthService(token);
    }

    private boolean validateTokenWithAuthService(String token) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8084/api/auth/authenticate";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            // Request to the authentication service
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return true;  // Valid token
            } else {
                System.out.println("Invalid token 1: " + response.getStatusCode());
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            throw new RuntimeException("An error occurred during token validation.");
        }
    }
}
*//*
package com.mehmetkaradana.springboot_api.services;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {

    public ResponseEntity<String> validateToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = (authHeader != null && authHeader.startsWith("Bearer ")) ? authHeader.substring(7) : null;

        // Check if the token is missing or empty
        if (token == null || token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is missing or not provided.");
        }

        return validateTokenWithAuthService(token);
    }

    private ResponseEntity<String> validateTokenWithAuthService(String token) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8084/api/auth/authenticate";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            // Request to the authentication service
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            // If the response is successful, return a valid token message
            if (response.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.ok("Valid token");
            } else {
                // Handle invalid token response
                String message = response.getBody() != null ? response.getBody() : "Invalid token.";
                return ResponseEntity.status(response.getStatusCode()).body(message);
            }
        } catch (Exception e) {
            // Log the error and return an internal server error
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during token validation.");
        }
    }
}*//*
package com.mehmetkaradana.springboot_api.services;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {

    public ResponseEntity<String> validateToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = (authHeader != null && authHeader.startsWith("Bearer ")) ? authHeader.substring(7) : null;

        // Token yoksa 401 Unauthorized hatası dön
        if (token == null || token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is missing or not provided.");
        }

        // Token'ı doğrula ve sonuç dön
        return validateTokenWithAuthService(token);
    }

    private ResponseEntity<String> validateTokenWithAuthService(String token) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8084/api/auth/authenticate";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            // Auth servisine token doğrulama isteği gönder
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            // Eğer doğrulama başarılıysa
            if (response.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.ok("Valid token");
            } else if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                // Geçersiz token için 401 Unauthorized dön
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
            } else {
                // Diğer HTTP hataları
                String message = response.getBody() != null ? response.getBody() : "Authentication service error.";
                return ResponseEntity.status(response.getStatusCode()).body(message);
            }
        } catch (Exception e) {
            // İstisna durumunda 500 Internal Server Error ve genel hata mesajı dön
            System.out.println("Error: " + e.getMessage());//bu çalıştığıu için else ifi eziyor  muhtemelen
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during token validation.");
        }
    }
}
*/
package com.mehmetkaradana.springboot_api.services;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {

    public ResponseEntity<String> validateToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = (authHeader != null && authHeader.startsWith("Bearer ")) ? authHeader.substring(7) : null;

        if (token == null || token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is missing or not provided.");
        }


        return validateTokenWithAuthService(token);
    }

    private ResponseEntity<String> validateTokenWithAuthService(String token) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://host.docker.internal:8084/api/auth/authenticate";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.ok("Valid token");
            }
            // Diğer durumlar
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());

        } catch (HttpClientErrorException e) {
            //400`s error
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
            } else {

                return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
            }
        } catch (Exception e) {
            // 5xx`s errors
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during token validation.");
        }
    }
}
