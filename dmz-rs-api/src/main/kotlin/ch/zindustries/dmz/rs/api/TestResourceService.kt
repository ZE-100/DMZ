package ch.zindustries.dmz.rs.api

import ch.zindustries.dmz.auth.annotations.AccessibleByAdmin
import ch.zindustries.dmz.auth.annotations.AccessibleByAnonymous
import ch.zindustries.dmz.auth.dtos.AccountDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("test")
interface TestResourceService {

    @AccessibleByAdmin
    @GetMapping("test-admin")
    fun testAdmin(): ResponseEntity<String>

    @AccessibleByAnonymous
    @GetMapping("test-user")
    fun testUser(): ResponseEntity<String>

    @AccessibleByAnonymous
    @PostMapping("create-test-users")
    fun createTestUsers(): ResponseEntity<List<AccountDTO>>
}
