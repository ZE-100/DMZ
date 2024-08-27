package ch.zindustries.dmz.auth.filters

import ch.zindustries.dmz.auth.dtos.DMZToken
import ch.zindustries.dmz.auth.dtos.DMZUserDetails
import ch.zindustries.dmz.auth.exceptions.SecurityContextException
import ch.zindustries.dmz.auth.services.JWTService
import com.google.gson.Gson
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Service
import java.io.BufferedWriter
import java.io.OutputStreamWriter

@Service
class AuthenticationFilter(
    authenticationManager: AuthenticationManager,
) : UsernamePasswordAuthenticationFilter(authenticationManager) {

    private val jwtService: JWTService = JWTService()

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

        val userDetails = authResult!!.principal as DMZUserDetails
        val account = userDetails.account

        val accessToken = jwtService.generateToken(account)
        val refreshToken = "TODO"
        val expiresIn = 60

        val dmzToken = DMZToken(accessToken, refreshToken, expiresIn)

        response!!.addHeader("Content-Type", "application/json")
        BufferedWriter(OutputStreamWriter(response.outputStream)).use {
            it.write(Gson().toJson(dmzToken))
        }
    }
}
