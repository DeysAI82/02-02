package ci.nsu.mobile.main.model

data class UserDto(
    val id: Int,
    val login: String,
    val email: String? = null,
    val token: String? = null
)
