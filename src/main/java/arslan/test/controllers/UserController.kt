package arslan.test.controllers

import arslan.test.domain.User
import arslan.test.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(val userService: UserService) {

    @PostMapping
    fun createUser(@RequestBody user: User) = userService.add(user)
        .andReturn {
            object {
                val id: Long = user.id!!
            }
        }

    private fun <R> Any.andReturn(block: Any.() -> R): R = this.run(block)

}