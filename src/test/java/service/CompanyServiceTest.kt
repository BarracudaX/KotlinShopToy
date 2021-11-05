package service

import arslan.test.config.KotlinShopConfig
import arslan.test.dao.CompanyDao
import arslan.test.domain.Company
import arslan.test.service.CompanyService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.transaction.annotation.Transactional
import utils.sameAs

@SpringBootTest(classes = [KotlinShopConfig::class],webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
class CompanyServiceTest @Autowired constructor(val sut: CompanyService, val dao: CompanyDao) {

    val company = Company("ANY_COMPANY_NAME")

    @Test
    internal fun `should insert the given company into the database`() {
        sut.add(company)
        dao.flush()

        val foundCompany = sut.findById(company.id!!)

        company sameAs  foundCompany
    }

    @Test
    internal fun `should remove the given company`() {
        sut.add(company)
        dao.flush()

        val companyId = company.id!!

        sut.remove(company)
        dao.flush()

        assertThrows<EmptyResultDataAccessException> { sut.findById(companyId) }
    }

    @Test
    internal fun `should update the given company`() {
        sut.add(company)
        dao.flush()

        val updatedCompany = company.copy("NEW_COMPANY_NAME")

        sut.update(updatedCompany)

        sut.findById(company.id!!) sameAs updatedCompany
    }


}