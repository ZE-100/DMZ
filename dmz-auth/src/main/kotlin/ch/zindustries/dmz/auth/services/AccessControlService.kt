package ch.zindustries.dmz.auth.services

import ch.zindustries.dmz.auth.dtos.utils.UserDTOUtil.Companion.canAccessService
import ch.zindustries.dmz.auth.exceptions.ServiceAccessDeniedException
import ch.zindustries.dmz.auth.types.ServiceType
import org.springframework.stereotype.Service

@Service
class AccessControlService(
    private val contextHolder: SecurityContextHolder,
) {

    fun assertCanAccessService(service: ServiceType) {

        val user = contextHolder.getUser()

        if (!user.canAccessService(service)) {
            throw ServiceAccessDeniedException("Cannot access ${service.serviceName()}")
        }
    }
}
