package arslan.test.dao

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.data.repository.Repository
import java.util.*

@NoRepositoryBean
interface Dao<T> : Repository<T, Long> {

    fun <S : T> save(entity: S): S

    fun findById(id: Long): Optional<T>

    fun deleteById(id: Long)

    fun delete(entity: T)

    fun findAll(pageable: Pageable): Page<T>

    fun flush()
}