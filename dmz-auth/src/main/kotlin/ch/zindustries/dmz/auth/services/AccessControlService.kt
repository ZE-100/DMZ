package ch.zindustries.dmz.auth.services

import ch.zindustries.dmz.auth.exceptions.ServiceAccessDeniedException
import ch.zindustries.dmz.auth.types.ServiceType
import org.springframework.stereotype.Service

@Service
class AccessControlService {

    fun assertCanAccessService(service: ServiceType) {

        throw ServiceAccessDeniedException("Cannot access ${service.serviceName()}")
    }
}
