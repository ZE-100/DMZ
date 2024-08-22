package ch.zindustries.dmz.rs.api

import ch.zindustries.dmz.auth.annotations.AccessibleByAdmin
import ch.zindustries.dmz.auth.annotations.AccessibleByUser
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("test")
interface TestResourceService {

    @AccessibleByAdmin
    @GetMapping("test-admin")
    fun testAdmin(): ResponseEntity<String>

    @AccessibleByUser
    @GetMapping("test-user")
    fun testUser(): ResponseEntity<String>
}
