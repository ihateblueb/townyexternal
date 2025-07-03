package site.remlit.blueb.townyexternal

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

import io.ktor.http.HttpStatusCode
import site.remlit.blueb.townyexternal.service.TownService

fun Application.configureRouting() {
    routing {
        get("/api/towns") {
            val towns = TownService.getTowns()

            if (towns.isEmpty())
                throw ApiException(HttpStatusCode.NoContent)

            call.respond(towns)
        }

        get("/api/town/:id") {
            throw ApiException(HttpStatusCode.NotImplemented)
        }

        get("/api/nations") {
            throw ApiException(HttpStatusCode.NotImplemented)
        }

        get("/api/nation/:id") {
            throw ApiException(HttpStatusCode.NotImplemented)
        }

        get("/api/residents") {
            throw ApiException(HttpStatusCode.NotImplemented)
        }

        get("/api/resident/:id") {
            throw ApiException(HttpStatusCode.NotImplemented)
        }
    }
}