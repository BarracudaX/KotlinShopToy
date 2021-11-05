package utils

import arslan.test.domain.Company
import arslan.test.domain.User
import org.junit.jupiter.api.assertAll
import java.time.LocalDate
import kotlin.random.Random
import kotlin.test.assertEquals

/**
 * Returns a localdate with random month and random day of month. The year set to be the year of birth of person that
 * has the given age. For example, if years = 18 and our current year is 2021, then the year of the returned local date
 * is going to be 2021 - 18 = 2003
 */
fun localDate(years: Int = 18)
        = LocalDate.of(LocalDate.now().year - years, Random.Default.randomMonth(), Random.Default.randomDayOfMonth())

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
        { assertEquals(this.username, other.username) },
        { assertEquals(this.password, other.password) },
        { assertEquals(this.firstName, other.firstName) },
        { assertEquals(this.lastName, other.lastName) },
        { assertEquals(this.birthDate, other.birthDate) }
    )
}

/**
 * Asserts that all fields(except the id field) of this company are the same(equal) as the values of fields of the given company.
 */
infix fun Company.sameAs(other: Company) {
    assertAll(
        {assertEquals(this.companyName,other.companyName)}
    )
}