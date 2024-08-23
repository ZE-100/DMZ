package ch.zindustries.dmz.auth.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table

@Entity
@Table(name = "authority")
class Authority : BaseEntity() {

    @Column(length = 255)
    var name: String = ""

    @Column(length = 255)
    var code: String = ""

    @ManyToMany(mappedBy = "authorities")
    var accounts: Set<Account> = HashSet()
}
