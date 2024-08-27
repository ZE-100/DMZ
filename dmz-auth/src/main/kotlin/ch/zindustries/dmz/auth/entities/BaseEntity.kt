package ch.zindustries.dmz.auth.entities

import jakarta.persistence.*
import java.util.*

@MappedSuperclass
open class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @Column
    var createdAt: Date = Date()

    @Column
    var updatedAt: Date = Date()
}
