package ch.zindustries.dmz.rs.server

import ch.zindustries.dmz.api.TestService
import ch.zindustries.dmz.rs.api.TestResourceService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class TestResourceServiceImpl(
    private val testService: TestService
) : TestResourceService {

    override fun test(): ResponseEntity<String> = ResponseEntity.ok(testService.test())
}
