package ch.zindustries.dmz.auth.entities

import jakarta.persistence.*

@Entity
@Table(name = "account")
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

    @ManyToMany
    @JoinTable(
        name = "authority_account",
        joinColumns = [JoinColumn(name = "authority_id")],
        inverseJoinColumns = [JoinColumn(name = "account_id")],
    )
    var authorities: Set<Authority> = HashSet()

    @ElementCollection
    var roles: Set<String> = HashSet()
}
