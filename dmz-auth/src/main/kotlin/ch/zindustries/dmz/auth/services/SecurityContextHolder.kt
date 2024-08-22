package ch.zindustries.dmz.auth.services

import ch.zindustries.dmz.auth.dtos.UserDTO
import ch.zindustries.dmz.auth.exceptions.SecurityContextException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class SecurityContextHolder {

    fun getUser(): UserDTO {
        return SecurityContextHolder.getContext()?.authentication?.principal as UserDTO
            ?: throw SecurityContextException("No security context found.")
    }
}
