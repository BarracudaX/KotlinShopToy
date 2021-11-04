package arslan.test

import arslan.test.config.KotlinShopConfig
import org.springframework.boot.runApplication
import java.io.File
import kotlin.io.path.Path

fun main() {
    runApplication<KotlinShopConfig>()
}