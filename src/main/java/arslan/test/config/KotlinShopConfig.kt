package arslan.test.config

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.time.LocalDate

@SpringBootApplication(scanBasePackages = ["arslan.test.controllers"])
@EnableTransactionManagement
class KotlinShopConfig {

}