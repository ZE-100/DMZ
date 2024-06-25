package ch.zindustries.dmz.auth.entities

import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "right")
class Right : BaseEntity() {

    @Column(length = 255)
    var code: String = ""

    @ManyToMany(mappedBy = "rights")
    var users: Set<User> = HashSet()
}
