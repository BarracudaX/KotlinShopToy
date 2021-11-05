package arslan.test.domain

import java.time.LocalDate
import javax.persistence.*
import javax.persistence.EnumType.STRING

@Entity
@Table(name = "USERS")
class User(@Column(unique = true) val username: String,
           val password:String,val firstName: String, val lastName: String, val birthDate: LocalDate) {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @field:Id
    var id: Long? = null

    override fun toString(): String {
        return "User(username='$username', firstName='$firstName', lastName='$lastName', birthDate=$birthDate, id=$id)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (username != other.username) return false

        return true
    }

    override fun hashCode(): Int {
        return username.hashCode()
    }

    fun copy( username: String = this.username, password:String = this.password,firstName: String = this.firstName,
              lastName: String = this.lastName, birthDate: LocalDate = this.birthDate) : User {
        return User(username,password,firstName,lastName,birthDate).apply { this.id = this@User.id }
    }

}

@Entity
@Table(name = "Companies")
class Company( val companyName: String) {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @field:Id
    var id: Long? = null


}

@Entity
@Table(name = "Products")
class Product (val productName: String, val price: Price, company: Company) {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @field:Id
    var id: Long? = null

    @JoinColumn(name = "company_id")
    @ManyToOne
    val company =  company

}

@Embeddable
class Price(val amount: Double, @Enumerated(STRING) val currency: Currency) {

}

enum class Currency {
    EUR,DOLLARS,YEN
}