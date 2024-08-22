package ch.zindustries.dmz.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = ["ch.zindustries.dmz"],
    exclude = [
        SecurityAutoConfiguration::class,
    ]
)
open class DmzAppApplication

fun main(args: Array<String>) {
    runApplication<DmzAppApplication>(*args)
}
