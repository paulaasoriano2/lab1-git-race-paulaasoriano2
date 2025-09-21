package es.unizar.webeng.hello

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


/* Integration tests to check the login functionality
 * First, using a valid user (configured in SecurityConfiguration.kt) to check that the login is successful and
 * redirects to the home page
 * Second, using an invalid user to check that the login fails and redirects to the login page with an error message
 */
@SpringBootTest
@AutoConfigureMockMvc
class LoginIntegrationTest(
    @Autowired val mockMvc: MockMvc
) {

    @Test
    fun `login with valid user should redirect to home`() {
        mockMvc.perform(
            formLogin("/login")
                .user("email", "paula@gmail.com")   // use of the parameters configured in SecurityConfiguration.kt
                .password("password", "pauli")      
        )
            .andExpect(status().is3xxRedirection)
            .andExpect(redirectedUrl("/"))          // a correct login redirects to the "/" page
    }

    @Test
    fun `login with invalid user should redirect to login error`() {
        mockMvc.perform(
            formLogin("/login")
                .user("email", "pepe@gmail.com")  // use of parameters not configured in SecurityConfiguration.kt
                .password("password", "contrasegna")
        )
            .andExpect(status().is3xxRedirection)
            .andExpect(redirectedUrl("/login?error=true")) // an incorrect login redirects to the login page with an error message
    }
}
