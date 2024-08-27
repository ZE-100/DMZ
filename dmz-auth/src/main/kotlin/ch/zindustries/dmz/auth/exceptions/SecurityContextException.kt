package ch.zindustries.dmz.auth.exceptions

import org.springframework.security.access.AccessDeniedException

class SecurityContextException(
    msg: String?,
    throwable: Throwable?,
) : AccessDeniedException(msg, throwable) {

    constructor(msg: String?) : this(msg, null)

    constructor() : this(null, null)
}
