package ch.zindustries.dmz

import ch.zindustries.dmz.api.TestService
import org.springframework.stereotype.Service

@Service
class TestServiceImpl : TestService {

    override fun test() = "Test"
}
