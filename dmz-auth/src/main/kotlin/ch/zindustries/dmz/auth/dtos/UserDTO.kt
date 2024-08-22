package ch.zindustries.dmz.auth.dtos

import java.util.*

data class UserDTO(
    var id: Long,
    var username: String,
    var email: String,
    var phone: String,
    var multiFactorActivated: Boolean,
    var rights: Set<RightDTO>,
    var roles: Set<String>,
    var createdAt: Date,
    var updatedAt: Date
)
