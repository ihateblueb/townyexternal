package site.remlit.blueb.townyexternal

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respondText

fun main(port: Int = 8064) {
    embeddedServer(Netty, port, host = "127.0.0.1", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            if (cause is ApiException) {
                call.respondText(text = cause.message ?: cause.status.description , status = cause.status)
                return@exception
            } else {
                call.respondText(text = cause.message ?: HttpStatusCode.InternalServerError.description , status = HttpStatusCode.InternalServerError)
                return@exception
            }
        }
    }

    configureRouting()
}
