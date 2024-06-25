package ch.zindustries.dmz.auth.repositories

import ch.zindustries.dmz.auth.entities.Right
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.LongSummaryStatistics

@Repository
interface RightRepository : JpaRepository<Right, LongSummaryStatistics>
