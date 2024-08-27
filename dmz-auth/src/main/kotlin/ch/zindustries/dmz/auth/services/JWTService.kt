package ch.zindustries.dmz.auth.services

import ch.zindustries.dmz.auth.dtos.AccountDTO
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.util.*

class JWTService {

    fun generateToken(account: AccountDTO): String {

        val token = JWT.create()
            .withIssuer("DMZ - JWTService")
            .withExpiresAt(Date(System.currentTimeMillis() + 60 * 1000))
            .withSubject(account.username)
            .withClaim("authorities", account.authorities.map { it.name })
            .sign(Algorithm.HMAC256("secret :)"))

        return token
    }
}
