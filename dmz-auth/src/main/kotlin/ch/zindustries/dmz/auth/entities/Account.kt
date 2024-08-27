package ch.zindustries.dmz.auth.entities

import ch.zindustries.dmz.auth.entities.listeners.AccountEntityListener
import jakarta.persistence.*

@Entity
@Table(name = "account")
@EntityListeners(AccountEntityListener::class)
class Account : BaseEntity() {

    @Column(length = 255, nullable = false)
    var username: String = ""

    @Column(length = 255, nullable = false)
    var password: String = ""

    @Column(length = 255, nullable = false)
    var email: String = ""

    @Column(length = 15)
    var phone: String? = null

    @Column
    var multiFactorActivated: Boolean = false

    @ManyToMany(
        cascade = [CascadeType.ALL],
    )
    @JoinTable(
        name = "authority_account",
        joinColumns = [JoinColumn(name = "authority_id")],
        inverseJoinColumns = [JoinColumn(name = "account_id")],
    )
    var authorities: Set<Authority> = HashSet()

    @ElementCollection
    var roles: Set<String> = HashSet()
}
