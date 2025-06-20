package ru.marchenko.studentportal.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import org.springframework.security.core.userdetails.UserDetails
import java.io.Serializable


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

	@Enumerated(EnumType.STRING)
	var role: Role? = null,

	var firstname: String? = null,

	var lastname: String? = null,

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "group_id")
	@JsonBackReference("user-group")
	var group: StudentGroup? = null,

	@OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL], mappedBy = "student")
	@JsonManagedReference("student-marks")
	var studentsMarks: MutableList<StudentsMarks> = mutableListOf()
) : UserDetails, Serializable {

	companion object {
		private val serialVersionUID = 354684123987534117L
	}

	override fun getAuthorities(): List<Role> {
		return listOf(role!!)
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