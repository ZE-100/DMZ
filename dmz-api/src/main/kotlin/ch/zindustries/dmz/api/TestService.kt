package ch.zindustries.dmz.api

import ch.zindustries.dmz.auth.dtos.AccountDTO

interface TestService {

    fun test(): String

    fun createTestUsers(): List<AccountDTO>
}
