package ch.zindustries.dmz

import ch.zindustries.dmz.api.TestService
import ch.zindustries.dmz.auth.dtos.AccountDTO
import ch.zindustries.dmz.auth.dtos.AuthorityDTO
import ch.zindustries.dmz.auth.entities.Account
import ch.zindustries.dmz.auth.entities.Authority
import ch.zindustries.dmz.auth.repositories.AccountRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class TestServiceImpl(
    private val accountRepository: AccountRepository,
) : TestService {

    override fun test(): String {
        return "Test"
    }

    @Transactional
    override fun createTestUsers(): List<AccountDTO> {

        val admin = Account()
        admin.username = "admin"
        admin.password = "admin"
        admin.email = "admin@zindustries.ch"
        admin.phone = null
        admin.multiFactorActivated = false
        val testAuthority = Authority()
        testAuthority.name = "test"
        testAuthority.code = "1"
        admin.authorities = setOf(testAuthority)
        admin.roles  = setOf("ROLE_ADMIN", "ROLE_USER")

        val user = Account()
        user.username = "user"
        user.password = "user"
        user.email = "user@zindustries.ch"
        user.phone = null
        user.multiFactorActivated = false
        val testAuthority1 = Authority()
        testAuthority1.name = "test1"
        testAuthority1.code = "2"
        user.authorities = setOf(testAuthority1)
        user.roles  = setOf("ROLE_USER")

        val saveAll = accountRepository.saveAll(listOf(admin, user))

        return saveAll.map {
            AccountDTO(
                it.id!!,
                it.username,
                it.email,
                it.phone,
                it.multiFactorActivated,
                it.authorities.map { auth -> AuthorityDTO(auth.name, auth.code) }.toSet(),
                it.roles,
                it.createdAt,
                it.updatedAt,
            )
        }
    }
}
