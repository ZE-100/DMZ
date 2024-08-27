package ch.zindustries.dmz.auth.dtos

data class DMZToken(
    var access_token: String,
    var refresh_token: String,
    var expires_in: Int,
)
