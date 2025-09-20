package es.unizar.webeng.hello.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping



/* Controller used to access to the login page through the path "/login"
*
*/
@Controller
class LoginController {
    @GetMapping("/login")
    fun login(): String {
        return "login"
    }
}
