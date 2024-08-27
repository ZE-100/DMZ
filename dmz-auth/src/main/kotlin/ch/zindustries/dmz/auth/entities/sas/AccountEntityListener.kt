package ch.zindustries.dmz.auth.entities.sas

import ch.zindustries.dmz.auth.entities.Account
import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class AccountEntityListener {

    @Lazy
    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @PrePersist
    @PreUpdate
    fun hashPassword(account: Account) {
        account.password = passwordEncoder.encode(account.password)
    }
}
