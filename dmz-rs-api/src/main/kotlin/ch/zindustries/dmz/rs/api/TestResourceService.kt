package ch.zindustries.dmz.rs.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping
interface TestResourceService {

    @GetMapping("test")
    fun test() : ResponseEntity<String>
}
