package arslan.test.config

import org.hibernate.SessionFactory
import org.hibernate.engine.spi.EntityEntryFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.hibernate5.HibernateTransactionManager
import org.springframework.orm.hibernate5.LocalSessionFactoryBean
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.io.File
import java.io.FileReader
import java.time.LocalDate
import java.util.*
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@SpringBootApplication(scanBasePackages = ["arslan.test.controllers"])
@EnableTransactionManagement
@EnableJpaRepositories("arslan.test.dao")
class KotlinShopConfig {

    @Bean
    fun entityManagerFactory(dataSource: DataSource) = LocalContainerEntityManagerFactoryBean().apply {
        setDataSource(dataSource)
        setPackagesToScan("arslan.test.domain")
        setJpaProperties(hibernateProperties())
        jpaVendorAdapter = HibernateJpaVendorAdapter()
    }

    @Bean
    fun transactionManager(emf: EntityManagerFactory) = JpaTransactionManager(emf)

    private fun hibernateProperties() = Properties().apply {
        put("hibernate.dialect","org.hibernate.dialect.H2Dialect")
        put("hibernate.hbm2ddl.auto","create")
    }

}