package es.unizar.webeng.hello.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfiguration {

    /**
     * Defines the security rules of the application requiring login for all requests
     * except: home, login, API, health and static files.
     * It also customs login form and logout behavior.
     */
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { requests -> requests
                .requestMatchers(
                    "/", "/login", "api/hello", "/actuator/health", 
                    "/css/**", "/js/**", "/images/**", 
                    "/webjars/**", "/resources/**", "/static/**", "/public/**", "/assets/**"
                ).permitAll()
                .anyRequest().authenticated()
            }
            .formLogin { form -> form
                .loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error=true") 
                .permitAll()
            }
            .logout { logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")  // to redirect to the main page page after logout
                .permitAll()
}


        return http.build()
    }

    /**
     * Creates an in-memory user:
     * - Email: paula@gmail.com
     * - Password: pauli
     * - Role: USER
     */
    @Bean
    fun userDetailsService(): UserDetailsService {
        val user: UserDetails = User.withDefaultPasswordEncoder()
            .username("paula@gmail.com")
            .password("pauli")
            .roles("USER")
            .build()

        return InMemoryUserDetailsManager(user)
    }
}
