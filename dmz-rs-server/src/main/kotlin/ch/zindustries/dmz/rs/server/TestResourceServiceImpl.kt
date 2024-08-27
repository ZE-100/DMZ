package ch.zindustries.dmz.rs.server

import ch.zindustries.dmz.api.TestService
import ch.zindustries.dmz.auth.dtos.AccountDTO
import ch.zindustries.dmz.rs.api.TestResourceService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class TestResourceServiceImpl(
    private val testService: TestService
) : TestResourceService {

    override fun testAdmin(): ResponseEntity<String> = ResponseEntity.ok(testService.test())

    override fun testUser(): ResponseEntity<String> = ResponseEntity.ok(testService.test())

    override fun createTestUsers(): ResponseEntity<List<AccountDTO>> = ResponseEntity.ok(testService.createTestUsers())
}
