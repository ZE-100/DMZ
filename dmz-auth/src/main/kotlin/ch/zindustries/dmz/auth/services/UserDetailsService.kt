package ch.zindustries.dmz.auth.services

import ch.zindustries.dmz.auth.exceptions.SecurityContextException
import ch.zindustries.dmz.auth.repositories.AccountRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.security.core.userdetails.UserDetailsService as SpringUserDetailsService

@Service
open class UserDetailsService(
    private val accountRepository: AccountRepository
) : SpringUserDetailsService {

    @Transactional
    override fun loadUserByUsername(username: String?): UserDetails {

        if (username.isNullOrEmpty()) {
            throw SecurityContextException("Username cannot be null or empty")
        }

        val account = accountRepository.findByUsername(username)
            ?: throw SecurityContextException("No user with username '$username' found.")

        return User(
            account.username,
            account.password,
            account.authorities.map { SimpleGrantedAuthority(it.name) }
        )
    }
}
