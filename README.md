Tour Agency API Documentation (2.0)

Overview 

This documentation provides details on how to interact with the endpoints available in the Tour Agency API. The API includes endpoints for user registration and fetching room data from various services. 

 

Base URLs 

Kotlin Service: http://localhost:8082 

Java Service: http://localhost:8081 

Go Service: http://localhost:8083 

Registration Service: http://localhost:8084 

 

Endpoints 

1. Register a User 

URL: http://localhost:8084/api/users/register 

Method: POST 

Content-Type: application/x-www-form-urlencoded 

Body Parameters: 

Key 

Type 

Description 

name 

String 

The user's name 

password 

String 

The user's password 

Example Request: 

http 

Kodu kopyala 

POST /api/users/register HTTP/1.1 
Host: localhost:8084 
Content-Type: application/x-www-form-urlencoded 
 
name=mehmet61616&password=123456 
 

 

2. Get Rooms (Kotlin Service) 

URL: http://localhost:8082/api/getRooms 

Method: GET 

Authorization: Bearer Token 

Headers: 

Key 

Value 

Authorization 

Bearer <your-token> 

Example Request: 

http 

Kodu kopyala 

GET /api/getRooms HTTP/1.1 
Host: localhost:8082 
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9... 
 

 

3. Get Rooms (Java Service) 

URL: http://localhost:8081/api/getRooms 

Method: GET 

Authorization: Bearer Token 

Headers: 

Key 

Value 

Authorization 

Bearer <your-token> 

Example Request: 

http 

Kodu kopyala 

GET /api/getRooms HTTP/1.1 
Host: localhost:8081 
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9... 
 

 

4. Get Rooms (Go Service) 

URL: http://localhost:8083/api/getRooms 

Method: GET 

Authorization: Bearer Token 

Headers: 

Key 

Value 

Authorization 

Bearer <your-token> 

Example Request: 

http 

Kodu kopyala 

GET /api/getRooms HTTP/1.1 
Host: localhost:8083 
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9... 
 

 

Notes 

Ensure to replace <your-token> with a valid JWT token. 

For testing, tools like Postman or curl can be used to send requests and verify responses. 

All endpoints require appropriate services to be running locally. 

You can run a server for each tourism agency by changing the port and agency name according to the following details: 

Servers List: 

yaml 

Kodu kopyala 

servers: 
  - name: "Istanbul Agency" 
    url: "http://host.docker.internal:3007" 
    distance_km: 10 
  - name: "Paris Agency" 
    url: "http://host.docker.internal:3001" 
    distance_km: 2005 
  - name: "New York Agency" 
    url: "http://host.docker.internal:3002" 
    distance_km: 8000 
  - name: "Tokyo Agency" 
    url: "http://host.docker.internal:3003" 
    distance_km: 9000 
  - name: "Sydney Agency" 
    url: "http://host.docker.internal:3004" 
    distance_km: 15000 
  - name: "Moscow Agency" 
    url: "http://host.docker.internal:3005" 
    distance_km: 3000 
  - name: "Cape Town Agency" 
    url: "http://host.docker.internal:3006" 
    distance_km: 8000 
 

For each server, simply change the port and agency name in the code and run the server. For example, for the Istanbul Agency, update the port to 3007 and the agency to "Istanbul Agency", then run the server using Node.js. 

Running the Servers 

Install dependencies: 

bash 

Kodu kopyala 

npm install express 
 

Save the server code in a file, for example, agency-server.js. 

Run the server: 

bash 

Kodu kopyala 

node agency-server.js 
 

 

Notes 

For testing, tools like Postman or curl can be used to send requests and verify responses. 

All endpoints require appropriate services to be running locally. 

Make sure to modify the port and agency name for each server. 
-------------------------------------------------------------------------------------------------------------------------------------------------------------------
GlobalTour (English)

Project Description

Project Name:

GlobalTour

Project Description:

Developing a REST API that collects price and room information from travel agencies worldwide and presents data to clients in JSON format.

Languages:

Java (Virtual Threads)

Kotlin (Coroutines)

Go (Goroutines)

Database:

MongoDB Atlas

User Authentication:

User authentication and access control using Bloom filter.

Technology and Tool Selection

API Frameworks:

Java: Spring Boot + Virtual Threads

Kotlin: Ktor + Coroutines

Go: Gin + Goroutines

Node.js: Express.js for configuring agency servers.

Database:

MongoDB Atlas

Authentication:

Bloom Filter: Preliminary querying to enhance performance.

MongoDB: User authentication and access control.

Node.js Auth Service: A dedicated Node.js service for user authentication and session management.

Management and Deployment:

Containerization: Applications are containerized using Docker.

Additional Features

Node.js Authentication Service

A Node.js-based authentication service is included in the project. This service is responsible for:

Managing user sessions.

Handling login and logout operations.

Validating API requests.

Providing JWT (JSON Web Tokens) for secure communication between clients and services.

Summary

GlobalTour integrates multiple technologies to build a scalable and efficient platform for travel data aggregation and presentation. It leverages advanced concurrency mechanisms, secure authentication methods, and a modular architecture to deliver robust and maintainable software.

GlobalTour (Türkçe)

Proje Tanımı

Proje Adı:

GlobalTour

Proje Tanımı:

Dünyadaki turizm acentelerinden fiyat ve oda bilgisi toplayarak, istemcilere JSON formatında veri sunacak bir REST API geliştirme.

Diller:

Java (Virtual Threads)

Kotlin (Coroutines)

Go (Goroutines)

Veritabanı:

MongoDB Atlas

Kullanıcı Doğrulama:

Bloom filter kullanarak kullanıcı doğrulama ve erişim kontrolü.

Teknoloji ve Araç Seçimi

API Frameworkleri:

Java: Spring Boot + Virtual Threads

Kotlin: Ktor + Coroutines

Go: Gin + Goroutines

Node.js: Acente sunucularının yapılandırılması için Express.js.

Veritabanı:

MongoDB Atlas

Kimlik Doğrulama:

Bloom Filter: Performansı artırmak için ön sorgulama.

MongoDB: Kullanıcı doğrulama ve erişim kontrolü.

Node.js Kimlik Doğrulama Servisi: Kullanıcı doğrulama ve oturum yönetimi için özel bir Node.js servisi.

Yönetim ve Dağıtım:

Containerization: Uygulamalar Docker kullanılarak containerize edilir.

Ek Özellikler

Node.js Kimlik Doğrulama Servisi

Projeye Node.js tabanlı bir kimlik doğrulama servisi dahildir. Bu servis şunlardan sorumludur:

Kullanıcı oturumlarını yönetmek.

Giriş ve çıkış işlemlerini yönetmek.

API isteklerini doğrulamak.

İstemciler ve servisler arasında güvenli iletişim için JWT (JSON Web Tokens) sağlamak.

Özet

GlobalTour, birden fazla teknolojiyi entegre ederek, seyahat verilerinin toplanması ve sunulması için ölçeklenebilir ve verimli bir platform oluşturur. Gelişmiş eşzamanlılık mekanizmalarını, güvenli kimlik doğrulama yöntemlerini ve modüler bir mimariyi kullanarak sağlam ve sürdürülebilir bir yazılım sunar.
