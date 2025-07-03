package site.remlit.blueb.townyexternal

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

import com.palmergames.bukkit.towny.TownyAPI
import io.ktor.http.HttpStatusCode

fun Application.configureRouting() {
    val towny = TownyAPI.getInstance()

    routing {
        get("/api/towns") {
            val towns = towny.towns
            var response = ""
            towns.forEach { town ->
                response += "${town.name}\n"
            }
            call.respondText(response)
        }

        get("/api/nations") {
            throw ApiException(HttpStatusCode.NotImplemented)
        }

        get("/api/residents") {
            throw ApiException(HttpStatusCode.NotImplemented)
        }
    }
}