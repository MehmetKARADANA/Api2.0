package com.mehmetkaradana



import com.mehmetkaradana.services.*
import io.ktor.client.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun main() {
    embeddedServer(Netty, port = 8082) {
        configureRouting()
    }.start(wait = true)
}

fun Routing.taskRoutes() {
    val client = HttpClient()
    val serversConfig = loadServersConfig("C:\\Users\\Mehmet\\Desktop\\Api2.0\\ktor-api\\src\\main\\resources\\servers.yaml")
 //   val serversConfig = loadServersConfig("src/main/resources/servers.yaml")
    route("/api") {
        get("/getRooms") {
            if (!validateToken(call)) {
                return@get
            }
            val responses = fetchFromAllServersAsJson(client, serversConfig.servers)
            call.respondText(responses)
        }
    }
}
fun Application.configureRouting() {
    routing {
        taskRoutes() // Task ile ilgili rotalar
      //  authRoutes() // Auth ile ilgili rotalar
    }
}