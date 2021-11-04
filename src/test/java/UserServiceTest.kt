import arslan.test.config.KotlinShopConfig
import arslan.test.dao.UserDao
import arslan.test.domain.User
import arslan.test.service.UserService
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Configuration
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.orm.jpa.EntityManagerFactoryUtils
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig
import org.springframework.test.context.transaction.BeforeTransaction
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.util.concurrent.ThreadLocalRandom
import kotlin.random.Random
import kotlin.random.asKotlinRandom
import kotlin.test.assertEquals

@SpringBootTest(classes = [KotlinShopConfig::class],webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
class UserServiceTest @Autowired constructor(val sut: UserService, val dao: UserDao) {

    val user = User("ANY_USERNAME","ANY_PASSWORD","ANY_FIRST_NAME","ANY_LAST_NAME", localDate(18))

    @Test
    internal fun `should add the given user to the database`() {
        sut.add(user)
        dao.flush()

        sut.findById(user.id ?: fail{"user id should not be null after insertion"}) sameAs user
    }

    @Test
    internal fun `should remove the given user from the database`() {
        sut.add(user)
        dao.flush()
        val userId = user.id!!

        sut.remove(user)
        dao.flush()

        assertThrows<EmptyResultDataAccessException> { sut.findById(userId) }
    }

    @Test
    internal fun `should update the given user`() {
        sut.add(user)
        dao.flush()

        val updatedUser = user.copy(username = "NEW_USERNAME",password = "NEW_PASSWORD", firstName = "NEW_FIRST_NAME",
                                    lastName = "NEW_LAST_NAME",birthDate = localDate(24))
        sut.update(updatedUser)
        dao.flush()

        println(sut.findById(user.id!!))
        sut.findById(user.id!!).sameAs(updatedUser)
    }

    /**
     * Returns a localdate with random month and random day of month. The year set to be the year of birth of person that
     * has the given age. For example, if years = 18 and our current year is 2021, then the year of the returned local date
     * is going to be 2021 - 18 = 2003
     */
    private fun localDate(years: Int = 18)
        = LocalDate.of(LocalDate.now().year - years, Random.Default.randomMonth(),Random.Default.randomDayOfMonth())

    /**
     * Returns a random number between 1 and 12
     */
    fun Random.randomMonth() = 1 + this.nextInt(12)

    /**
     * Returns random number between 1 and 28.
     */
    fun Random.randomDayOfMonth() = 1 + this.nextInt(28)


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