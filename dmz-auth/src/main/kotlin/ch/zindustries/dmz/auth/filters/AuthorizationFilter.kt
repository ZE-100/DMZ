package ch.zindustries.dmz.auth.filters

import ch.zindustries.dmz.auth.dtos.DMZUserDetails
import ch.zindustries.dmz.auth.exceptions.SecurityContextException
import com.auth0.jwt.JWT
import com.auth0.jwt.exceptions.JWTDecodeException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.web.filter.OncePerRequestFilter
import java.util.*

@Service
class AuthorizationFilter(
    private val userDetailsService: UserDetailsService,
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val bearer = request.getHeader(AUTHORIZATION)

        if (bearer.isNullOrEmpty()) {
            logger.info("No token provided. Skipping authorization.")
            filterChain.doFilter(request, response)
            return
        }

        if (!bearer.startsWith("Bearer ")) {
            throw SecurityContextException("Invalid Bearer token provided")
        }

        val decodedBearer = try {
            JWT.decode(bearer.removePrefix("Bearer "))
        } catch (ex: JWTDecodeException) {
            throw SecurityContextException("Invalid Bearer token provided", ex)
        }

        // TODO improve readability
        val subject = decodedBearer.getClaim("sub").asString()
        val expiry = decodedBearer.getClaim("exp").asDate()

        if (expiry.before(Date())) {
            throw SecurityContextException("Bearer Token expired")
        }

        val userDetails = userDetailsService.loadUserByUsername(subject) as DMZUserDetails
        userDetails.eraseCredentials()

        if (
            !userDetails.isEnabled ||
            !userDetails.isAccountNonExpired ||
            !userDetails.isCredentialsNonExpired ||
            !userDetails.isAccountNonLocked
        ) {
            // TODO: Actually implement
            throw SecurityContextException("Account is expired")
        }

        val authenticatedToken = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.account.roles.map { SimpleGrantedAuthority(it) })

        SecurityContextHolder.getContext().authentication = authenticatedToken

        filterChain.doFilter(request, response)
    }
}
