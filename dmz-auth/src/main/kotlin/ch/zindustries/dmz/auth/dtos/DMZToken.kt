package ch.zindustries.dmz.auth.dtos

// TODO: Replace by keycloak stuff
data class DMZToken(
    var access_token: String,
    var refresh_token: String,
    var expires_in: Int,
)
