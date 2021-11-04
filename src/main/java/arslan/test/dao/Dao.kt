package arslan.test.dao

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface Dao<T> : JpaRepository<T,Long> {

}