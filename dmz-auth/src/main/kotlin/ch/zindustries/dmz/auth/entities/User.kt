package ch.zindustries.dmz.auth.entities

import jakarta.persistence.*

@Entity
@Table(name = "user")
class User : BaseEntity() {

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
        name = "right_user",
        joinColumns = [JoinColumn(name = "right_id")],
        inverseJoinColumns = [JoinColumn(name = "user_id")],
    )
    var rights: Set<Right> = HashSet()

    @ElementCollection
    var roles: Set<String> = HashSet()
}
