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


}