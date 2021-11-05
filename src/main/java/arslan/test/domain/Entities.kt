package arslan.test.domain

import java.time.LocalDate
import javax.persistence.*
import javax.persistence.EnumType.STRING

@Entity
@Table(name = "USERS")
data class User(

    @Column(unique = true) val username: String,
    val password: String,

    val firstName: String,

    val lastName: String,

    val birthDate: LocalDate,

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @field:Id
    val id: Long? = null
) {


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
}

@Entity
@Table(name = "Companies")
data class Company(
    @Column(unique = true) val companyName: String,

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @field:Id
    val id: Long? = null
) {


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Company

        if (companyName != other.companyName) return false

        return true
    }

    override fun hashCode(): Int {
        return companyName.hashCode()
    }

}

@Entity
@Table(name = "Products")
data class Product(
    val productName: String, val price: Price,

    @JoinColumn(name = "company_id")
    @ManyToOne
    val company: Company,

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @field:Id
    val id: Long? = null

)

@Embeddable
data class Price(val amount: Double, @Enumerated(STRING) val currency: Currency)

enum class Currency {
    EUR, DOLLARS, YEN
}