package ch.zindustries.dmz.app.configs

import ch.zindustries.dmz.auth.filters.AuthenticationFilter
import ch.zindustries.dmz.auth.filters.AuthorizationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

@Configuration
@EnableWebSecurity
open class SecurityConfig {

    private val AUTH_WHITELIST = arrayOf(
        "/anonymous/**",
        "/test/**"
    )

    @Bean
    open fun filterChain(
        httpSecurity: HttpSecurity,
        userDetailsService: UserDetailsService,
        jwtAuthenticationFilter: AuthenticationFilter,
    ): SecurityFilterChain {

        jwtAuthenticationFilter.setFilterProcessesUrl("/auth/login")

        val authorizationFilter = AuthorizationFilter(userDetailsService)

        httpSecurity
            .csrf { it.disable() }
            .httpBasic { it.disable() }
            .formLogin { it.disable() }
            .authorizeHttpRequests {
                it.requestMatchers(*AUTH_WHITELIST).permitAll()
                    .anyRequest().authenticated()
            }
            .addFilterBefore(jwtAuthenticationFilter, BasicAuthenticationFilter::class.java)
            .addFilterAfter(authorizationFilter, AuthenticationFilter::class.java)

        return httpSecurity.build()
    }

    @Bean
    open fun authenticationManager(
        authConfig: AuthenticationConfiguration
    ): AuthenticationManager = authConfig.authenticationManager

    @Bean
    open fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}
