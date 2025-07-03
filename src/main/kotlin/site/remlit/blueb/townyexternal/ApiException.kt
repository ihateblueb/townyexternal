package site.remlit.blueb.townyexternal

import io.ktor.http.HttpStatusCode

class ApiException(val status: HttpStatusCode, message: String? = null) : Exception(message)