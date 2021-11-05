package arslan.test.service

import arslan.test.dao.CompanyDao
import arslan.test.dao.Dao
import arslan.test.domain.Company
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.transaction.annotation.Transactional

sealed interface CompanyService : Service<Company> {

    @org.springframework.stereotype.Service
    @Transactional
    class CompanyServiceImpl(val companyDao: CompanyDao) : CompanyService {

        override fun add(company: Company) {
            companyDao.save(company)
        }

        override fun remove(company: Company) = companyDao.delete(company)

        @Transactional(readOnly = true)
        override fun findAll(pageable: Pageable): Page<Company> {
            TODO("Not yet implemented")
        }

        override fun update(company: Company) {
            companyDao.save(company)
        }

        override fun execute(block: Dao<Company>.() -> Unit) {
            TODO("Not yet implemented")
        }

        override fun <R> executeAndGet(block: Dao<Company>.() -> R): R {
            TODO("Not yet implemented")
        }

        @Transactional(readOnly = true)
        override fun findById(id: Long): Company {
            return companyDao.findById(id).orElseThrow{ EmptyResultDataAccessException("Company with id $id not found",1)}
        }


    }

}