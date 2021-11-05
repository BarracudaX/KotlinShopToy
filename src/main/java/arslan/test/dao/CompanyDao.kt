package arslan.test.dao

import arslan.test.domain.Company
import org.springframework.stereotype.Repository

@Repository
interface CompanyDao : Dao<Company> {

}