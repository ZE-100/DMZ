package ch.zindustries.dmz

import ch.zindustries.dmz.api.TestService
import ch.zindustries.dmz.auth.services.AccessControlService
import ch.zindustries.dmz.auth.types.TestServices
import org.springframework.stereotype.Service

@Service
class TestServiceImpl(
    private val accessControl: AccessControlService,
) : TestService {

    override fun test(): String {

        accessControl.assertCanAccessService(TestServices.TEST_SERVICE)

        return "Test"
    }
}
