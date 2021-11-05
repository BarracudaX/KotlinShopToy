package arslan.test.dao

import arslan.test.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserDao : Dao<User> {

    fun findByUsername(username: String): User

}