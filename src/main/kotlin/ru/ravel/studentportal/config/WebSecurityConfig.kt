package ru.ravel.studentportal.config


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.*
import org.springframework.security.core.session.SessionRegistry
import org.springframework.security.core.session.SessionRegistryImpl
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.csrf.CookieCsrfTokenRepository
import org.springframework.security.web.savedrequest.HttpSessionRequestCache
import ru.ravel.studentportal.service.AuthService


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class WebSecurityConfig(
	private val authService: AuthService,
) {

	@Bean
	fun userDetailsService(): UserDetailsService {
		return authService
	}

	@Bean
	fun authenticationProvider(
		userDetailsService: UserDetailsService,
		passwordEncoder: PasswordEncoder
	): AuthenticationProvider {
		return DaoAuthenticationProvider().apply {
			setUserDetailsService(userDetailsService)
			setPasswordEncoder(passwordEncoder)
		}
	}


	@Bean
	fun passwordEncoder(): PasswordEncoder {
		return BCryptPasswordEncoder(12)
	}


	@Bean
	fun sessionRegistry(): SessionRegistry {
		return SessionRegistryImpl()
	}


	@Bean
	fun authenticationManager(http: HttpSecurity): AuthenticationManager {
		val builder = http.getSharedObject(AuthenticationManagerBuilder::class.java)
		builder.userDetailsService(authService).passwordEncoder(passwordEncoder())
		return builder.build()
	}


	@Bean
	@Throws(Exception::class)
	fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
		val requestCache = HttpSessionRequestCache()
		requestCache.setMatchingRequestParameterName(null)
		http
			.csrf { csrf ->
				csrf
					.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
					.ignoringRequestMatchers("/api/**")
//				    .disable()
			}
//			.cors { cors ->
//				cors.disable()
//			}
			.authorizeHttpRequests { requests ->
				requests
					.requestMatchers("/js/**", "/css/**", "/icons/**", "/fonts/**").permitAll()
					.requestMatchers("/login", "/perform_login").permitAll()
					.anyRequest().authenticated()
			}
			.formLogin { formLogin ->
				formLogin
					.loginPage("/login")
					.loginProcessingUrl("/perform_login")
					.failureUrl("/login?error=true")
					.permitAll()
			}
			.logout { logout ->
				logout.logoutSuccessUrl("/login?logout=true")
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID")
					.permitAll()
			}
			.requestCache { cache ->
				cache.requestCache(requestCache)
			}
		return http.build()
	}

}