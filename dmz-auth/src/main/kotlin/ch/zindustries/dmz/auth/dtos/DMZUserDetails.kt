package ch.zindustries.dmz.auth.dtos

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User

class DMZUserDetails(
    account: AccountDTO,
    password: String?,
    enabled: Boolean = true,
    accountNonExpired: Boolean = true,
    credentialsNonExpired: Boolean = true,
    accountNonLocked: Boolean = true,
) : User(
    account.username,
    password,
    enabled,
    accountNonExpired,
    credentialsNonExpired,
    accountNonLocked,
    account.authorities.map { SimpleGrantedAuthority(it.name) }
)
