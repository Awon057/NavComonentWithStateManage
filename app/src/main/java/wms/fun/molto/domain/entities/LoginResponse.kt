package wms.`fun`.molto.domain.entities

data class LoginResponse(
    val message: String,
    val program: String,
    val release: String,
    val response: Response,
    val status: String,
    val version: String
)