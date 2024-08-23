package ch.zindustries.dmz.auth.repositories

import ch.zindustries.dmz.auth.entities.Authority
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorityRepository : JpaRepository<Authority, Long>
