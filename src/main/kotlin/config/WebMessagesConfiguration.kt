package es.unizar.webeng.hello.config


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor
import org.springframework.web.servlet.i18n.SessionLocaleResolver
import java.util.Locale

/**
* Personalization of the MVC configuration to support internationalization.
*/
@Configuration class WebMessagesConfiguration : WebMvcConfigurer {

    /**
    * Configures the default locale resolver to be a session-based resolver with a default locale of English.
    *
    * @return the LocaleResolver configured.
    */
    @Bean fun localeResolver(): LocaleResolver {
        val localeResolver = SessionLocaleResolver()
        localeResolver.setDefaultLocale(Locale.ENGLISH)
        return localeResolver
    }

    /**
    * Method which defines an interceptor, which allows changing the current locale.
    *
    * @return LocaleChangeInterceptor with the parameter name set to "lang".
    */
    @Bean fun localeChangeInterceptor(): LocaleChangeInterceptor {
        val localeInterceptor = LocaleChangeInterceptor()
        localeInterceptor.paramName = "lang"
        return localeInterceptor
    }
    
    /**
    * Method used to add the interceptor previously defined to the Spring MVC registry.
    *
    * @param registry MVC interceptors registry.
    * @see localeChangeInterceptor().
    */
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(localeChangeInterceptor())
    }
}
