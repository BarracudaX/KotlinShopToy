import arslan.test.config.KotlinShopConfig
import arslan.test.dao.UserDao
import arslan.test.domain.User
import arslan.test.service.UserService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.fail
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Configuration
import org.springframework.orm.jpa.EntityManagerFactoryUtils
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig
import org.springframework.test.context.transaction.BeforeTransaction
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import kotlin.test.assertEquals

@SpringBootTest(classes = [KotlinShopConfig::class],webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
class UserServiceTest @Autowired constructor(val sut: UserService, val dao: UserDao) {

    @Test
    internal fun `should add the given user to the database`() {
        val user
            = User("ANY_USERNAME","ANY_PASSWORD","ANY_FIRST_NAME","ANY_LAST_NAME", localDate(18))

        sut.add(user)
        dao.flush()

        sut.findById(user.id ?: fail{"user id should not be null after insertion"}) sameAs user
    }

    fun localDate(years: Int = 18, month: Int = 2 , dayOfMonth: Int = 28)
        = LocalDate.of(LocalDate.now().year - years,month,dayOfMonth)

    /**
     * Asserts that all fields(except the id field) of this user are the same as the values of fields of the given user
     */
    infix fun User.sameAs(other: User) {
        assertAll(
            { assertEquals(this.username, other.username)},
            { assertEquals(this.password, other.password)},
            { assertEquals(this.firstName, other.firstName)},
            { assertEquals(this.lastName, other.lastName)},
            { assertEquals(this.birthDate, other.birthDate)}
        )
    }

}