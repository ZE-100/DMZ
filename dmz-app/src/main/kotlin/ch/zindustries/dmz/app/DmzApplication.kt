package ch.zindustries.dmz.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = ["ch.zindustries.dmz"]
)
open class DmzAppApplication

fun main(args: Array<String>) {
    runApplication<DmzAppApplication>(*args)
}
