package ch.zindustries.dmz.auth.repositories

import ch.zindustries.dmz.auth.entities.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : JpaRepository<Account, Long>
