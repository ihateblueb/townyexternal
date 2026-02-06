package site.remlit.townyexternal

import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.cors.routing.CORS
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond
import kotlinx.serialization.json.Json
import site.remlit.townyexternal.model.ApiError
import site.remlit.townyexternal.model.ApiException

fun main() {
    val host = TownyExternal.instance.config.get("http-address")?.toString() ?: "0.0.0.0"
    val port = TownyExternal.instance.config.get("http-port")?.toString()?.toInt() ?: 8064

    embeddedServer(Netty, port, host, module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) {
        json(Json {
            encodeDefaults = true
            prettyPrint = true
            isLenient = true
        })
    }

    install(StatusPages) {
        exception<Throwable> { call, cause ->
            if (cause is ApiException) {
                call.respond(status = cause.status, message = ApiError(
                    cause.message,
                    cause.stackTraceToString()
                )
                )
                return@exception
            } else {
                call.respond(status = HttpStatusCode.InternalServerError, message = ApiError(
                    cause.message,
                    cause.stackTraceToString()
                )
                )
                return@exception
            }
        }
    }

    install(CORS) {
        anyHost()
        anyMethod()
    }

    configureRouting()
}
