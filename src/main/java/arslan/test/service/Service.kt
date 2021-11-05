package arslan.test.service

import arslan.test.dao.Dao
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface Service<T> {

    infix fun add(t: T)

    infix fun remove(t: T)

    infix fun findAll(pageable: Pageable): Page<T>

    infix fun update(t: T)

    infix fun execute(block: Dao<T>.() -> Unit)

    infix fun <R> executeAndGet(block: Dao<T>.() -> R) : R

    infix fun findById(id: Long) : T

}