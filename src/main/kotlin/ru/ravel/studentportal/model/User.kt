package ru.ravel.studentportal.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.springframework.security.core.userdetails.UserDetails


@Entity
@Table(name = "user_t")
class User(

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Long? = null,

	@Column(unique = true)
	private var email: String? = null,

	@JsonIgnore
	private var password: String? = null,

	@ElementCollection(fetch = FetchType.EAGER, targetClass = Role::class)
	@Enumerated(EnumType.STRING)
	@CollectionTable(name = "user_authorities", joinColumns = [JoinColumn(name = "user_id")])
	@Column(name = "authorities")
	var roles: List<Role> = mutableListOf(),

	var firstname: String? = null,

	var lastname: String? = null,
) : UserDetails {

	override fun getAuthorities(): List<Role> {
		return roles
	}


	override fun getPassword(): String {
		return password ?: throw RuntimeException("Password is null")
	}

	override fun getUsername(): String {
		return email ?: throw RuntimeException("Email is null")
	}


	fun setPassword(password: String?) {
		if (password?.isNotEmpty() == true) {
			this.password = password
		} else {
			throw RuntimeException("Password is empty")
		}
	}
}