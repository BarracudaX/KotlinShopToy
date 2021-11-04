package arslan.test.domain

import java.time.LocalDate
import javax.persistence.*

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


}