package ch.zindustries.dmz.auth.services

import ch.zindustries.dmz.auth.dtos.AccountDTO
import ch.zindustries.dmz.auth.dtos.AuthorityDTO
import ch.zindustries.dmz.auth.dtos.DMZUserDetails
import ch.zindustries.dmz.auth.exceptions.SecurityContextException
import ch.zindustries.dmz.auth.repositories.AccountRepository
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

        // TODO: Replace by mapstruct
        return DMZUserDetails(
            AccountDTO(
                account.id!!,
                account.username,
                account.email,
                account.phone,
                account.multiFactorActivated,
                account.authorities.map { AuthorityDTO(it.name, it.code) }.toSet(),
                account.roles,
                account.createdAt,
                account.updatedAt,
            ),
            account.password
        )
    }
}
