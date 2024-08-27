package ch.zindustries.dmz.auth.dtos

import ch.zindustries.dmz.auth.types.ServiceType
import java.util.*

data class AccountDTO(
    var id: Long,
    var username: String,
    var email: String,
    var phone: String,
    var multiFactorActivated: Boolean,
    var authorities: Set<AuthorityDTO>,
    var roles: Set<String>,
    var createdAt: Date,
    var updatedAt: Date
)


fun AccountDTO.hasRight(right: AuthorityDTO) = this.authorities.contains(right)

fun AccountDTO.canAccessService(serviceRight: ServiceType) =
    this.authorities.find { it.name == serviceRight.serviceName() } != null
