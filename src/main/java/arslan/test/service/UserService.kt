package arslan.test.service

import arslan.test.dao.Dao
import arslan.test.dao.UserDao
import arslan.test.domain.User
import org.springframework.dao.DataAccessException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.transaction.annotation.Transactional

sealed interface UserService : Service<User> {

    @Transactional
    @org.springframework.stereotype.Service
    class UserServiceImpl(val userDao: UserDao) : UserService {

        override fun add(user: User) {
            userDao.save(user)
        }

        override fun remove(user: User) = userDao.delete(user)

        override fun findAll(pageable: Pageable): Page<User> = userDao.findAll(pageable)

        override fun update(user: User) {
            userDao.save(user)
        }

        override fun execute(block: Dao<User>.() -> Unit) = userDao.block()

        override fun <R> executeAndGet(block: Dao<User>.() -> R) : R = userDao.block()

        override fun findById(id: Long): User = userDao.findById(id)
            .orElseThrow{ EmptyResultDataAccessException("User with id $id not found",1) }

    }
}

