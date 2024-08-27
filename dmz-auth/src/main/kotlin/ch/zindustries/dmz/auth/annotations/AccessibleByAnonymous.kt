package ch.zindustries.dmz.auth.annotations

import ch.zindustries.dmz.auth.Roles
import org.springframework.security.access.annotation.Secured

@Secured(value = [Roles.ANONYMOUS])
annotation class AccessibleByAnonymous
