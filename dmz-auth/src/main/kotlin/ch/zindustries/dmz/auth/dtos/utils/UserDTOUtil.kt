package ch.zindustries.dmz.auth.dtos.utils

import ch.zindustries.dmz.auth.dtos.RightDTO
import ch.zindustries.dmz.auth.dtos.UserDTO
import ch.zindustries.dmz.auth.types.ServiceType

class UserDTOUtil {
    companion object {

        fun UserDTO.hasRight(right: RightDTO) = this.rights.contains(right)

        fun UserDTO.canAccessService(serviceRight: ServiceType) =
            this.rights.find { it.name == serviceRight.serviceName() } != null
    }
}
