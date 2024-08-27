package ch.zindustries.dmz.auth.services

import ch.zindustries.dmz.auth.dtos.AccountDTO
import ch.zindustries.dmz.auth.exceptions.SecurityContextException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class SecurityContextHolder {

    fun getUser(): AccountDTO {
        return SecurityContextHolder.getContext()?.authentication?.principal as AccountDTO?
            ?: throw SecurityContextException("No security context found.")
    }
}
