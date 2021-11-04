package arslan.test.domain

import javax.persistence.*

@Entity
@Table(name = "USERS")
class User(val username: String,val password:String) {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @field:Id
    var id: Long? = null

}