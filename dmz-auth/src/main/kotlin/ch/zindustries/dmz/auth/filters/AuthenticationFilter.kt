package ch.zindustries.dmz.auth.filters

import ch.zindustries.dmz.auth.exceptions.SecurityContextException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Service

@Service
class AuthenticationFilter(
    authenticationManager: AuthenticationManager,
) : UsernamePasswordAuthenticationFilter(authenticationManager) {

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {

        val username = super.obtainUsername(request)
        val password = super.obtainPassword(request)

        if (username == null || password == null) {
            throw SecurityContextException("Username and password must not be null")
        }

        val unauthenticatedToken = UsernamePasswordAuthenticationToken.unauthenticated(username, password)
        setDetails(request, unauthenticatedToken)

        return authenticationManager.authenticate(unauthenticatedToken)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        super.successfulAuthentication(request, response, chain, authResult)
    }

    override fun unsuccessfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        failed: AuthenticationException?
    ) {
        super.unsuccessfulAuthentication(request, response, failed)
    }
}
