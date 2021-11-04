package wms.`fun`.molto.domain.entities

data class User(
    val activated: Int,
    val created_at: String,
    val email: String,
    val id: Int,
    val last_activity: Any,
    val name: String,
    val phone: String,
    val profile_picture: Any,
    val role: Role,
    val role_id: Int,
    val updated_at: String
)