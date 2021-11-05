package controller

import arslan.test.config.KotlinShopConfig
import arslan.test.domain.User
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpOutputMessage
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultMatcher
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.util.ResizableByteArrayOutputStream
import java.io.OutputStream
import service.UserServiceTest.*
import utils.localDate
import java.io.CharArrayWriter
import java.io.Writer
import kotlin.test.assertContains
import kotlin.test.assertTrue

@SpringBootTest(classes = [KotlinShopConfig::class])
@AutoConfigureMockMvc
class UserControllerTest @Autowired constructor(val mockMvc: MockMvc,val converter: ObjectMapper) {

    val user = User("ANY_USERNAME","ANY_PASSWORD","ANY_FIRST_NAME","ANY_LAST_NAME", localDate(18))
    val userJson: String = converter.writeValueAsString(user)

    @Test
    internal fun `should insert the given user to the database and return id of the inserted user in response`() {
        val response = mockMvc.post("/users") {
            content = userJson
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
        }.andReturn().response.contentAsString

        assertContains(response,"\"id\":")
    }
}