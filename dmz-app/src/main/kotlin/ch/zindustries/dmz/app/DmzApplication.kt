package ch.zindustries.dmz.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity

@SpringBootApplication(
    scanBasePackages = ["ch.zindustries.dmz"],
    exclude = [
        SecurityAutoConfiguration::class,
    ]
)
@EntityScan("ch.zindustries.dmz")
@EnableJpaRepositories("ch.zindustries.dmz")
@EnableMethodSecurity(securedEnabled = true)
open class DmzAppApplication

fun main(args: Array<String>) {
    runApplication<DmzAppApplication>(*args)
}
