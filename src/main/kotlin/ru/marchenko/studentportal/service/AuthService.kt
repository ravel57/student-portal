package ru.marchenko.studentportal.service


import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import ru.marchenko.studentportal.repository.UserRepository


@Service
class AuthService(
	private val repository: UserRepository,
) : UserDetailsService {

	@Throws(UsernameNotFoundException::class)
	override fun loadUserByUsername(email: String): UserDetails {
		return repository.findByEmail(email.trim().lowercase())
			?: throw UsernameNotFoundException("Incorrect login")
	}

}