package es.unizar.webeng.hello.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.security.core.Authentication


@Controller
class HelloController(
    @param:Value("\${app.message:Hello World}") 
    private val message: String
) {
    
    @GetMapping("/")
    fun welcome(
        model: Model,
        @RequestParam(defaultValue = "") name: String,
        authentication: Authentication?
    ): String {
        val greeting = if (name.isNotBlank()) "Hello, $name!" else message
        model.addAttribute("message", greeting)
        model.addAttribute("name", name)

        // New attribute to indicate if the user is logged in
        model.addAttribute("loggedIn", authentication != null)
        return "welcome"
    }
}

@RestController
class HelloApiController {
    
    @GetMapping("/api/hello", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun helloApi(@RequestParam(defaultValue = "World") name: String): Map<String, String> {
        return mapOf(
            "message" to "Hello, $name!",
            "timestamp" to java.time.Instant.now().toString()
        )
    }
}
