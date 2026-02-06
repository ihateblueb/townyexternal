package site.remlit.townyexternal

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

import io.ktor.http.HttpStatusCode
import io.ktor.server.util.getOrFail
import site.remlit.townyexternal.model.ApiException
import site.remlit.townyexternal.service.NationService
import site.remlit.townyexternal.service.TownService

fun Application.configureRouting() {
    routing {
        get("/api/towns") {
            val towns = TownService.getTowns()
            if (towns.isEmpty())
                throw ApiException(HttpStatusCode.NoContent)

            call.respond(towns)
        }

        get("/api/town/{id}") {
            val town = TownService.getTown(call.parameters.getOrFail("id"))
            if (town == null)
                throw ApiException(HttpStatusCode.NotFound)

            call.respond(town)
        }

        get("/api/nations") {
            val nations = NationService.getNations()
            if (nations.isEmpty())
                throw ApiException(HttpStatusCode.NoContent)

            call.respond(nations)
        }

        get("/api/nation/{id}") {
            val nation = NationService.getNation(call.parameters.getOrFail("id"))
            if (nation == null)
                throw ApiException(HttpStatusCode.NotFound)

            call.respond(nation)
        }

        get("/api/residents") {
            throw ApiException(HttpStatusCode.NotImplemented)
        }

        get("/api/resident/{id}") {
            throw ApiException(HttpStatusCode.NotImplemented)
        }
    }
}