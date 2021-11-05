package service

import arslan.test.config.KotlinShopConfig
import arslan.test.dao.UserDao
import arslan.test.domain.User
import arslan.test.service.UserService
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.jdbc.Sql
import org.springframework.transaction.annotation.Transactional
import utils.localDate
import utils.sameAs
import utils.usersSameAs
import java.awt.print.Pageable
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@SpringBootTest(classes = [KotlinShopConfig::class],webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
class UserServiceTest @Autowired constructor(val sut: UserService, val dao: UserDao) {

    val user = User("ANY_USERNAME","ANY_PASSWORD","ANY_FIRST_NAME","ANY_LAST_NAME", localDate(18))
    val users = mutableListOf<User>()

    @BeforeEach
    fun setUp() {
        for(x in 1..20) {
            val user = User("USERNAME$x","PASSWORD$x","FIRST_NAME$x","LAST_NAME$x", localDate(18))
            dao.save(user)
            users.add(user)
        }
        dao.flush()
    }

    @Test
    internal fun `should return page containing the given number of users(if possible)`() {
        val request = PageRequest.of(2, 5)
        val page = sut.findAll(request)

        page.content usersSameAs users.subList(10, 15)
    }

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

        sut.findById(user.id!!).sameAs(updatedUser)
    }



}