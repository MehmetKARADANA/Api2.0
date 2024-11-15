package com.mehmetkaradana.services

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

suspend fun validateToken(call: ApplicationCall): Boolean {
    val token = call.request.headers["Authorization"]?.substringAfter("Bearer ")
  //  println("Gelen Token: $token")
    if (token.isNullOrEmpty()) {
        call.respond(HttpStatusCode.Unauthorized, "Token is missing or not provided.")
        return false
    }
    return validateTokenWithAuthService(token, call)
}
suspend fun validateTokenWithAuthService(token: String, call: ApplicationCall): Boolean {
    val client = HttpClient()

    return try {
        val response: HttpResponse = client.get("http://host.docker.internal:8084/api/auth/authenticate") {
            header("Authorization", "Bearer $token")
        }
        if (response.status == HttpStatusCode.OK) {
            true // Geçerli token
        } else {
            call.respond(response.status, "invalid token: ${response.status}")
            false
        }
    } catch (e: Exception) {
        println("Error: ${e.message}")
        false
    } finally {
        client.close()
    }
}