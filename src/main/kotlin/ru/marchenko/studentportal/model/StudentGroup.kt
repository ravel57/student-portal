package ru.marchenko.studentportal.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import java.io.Serializable


@Entity
data class StudentGroup(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Long? = null,

	var name: String? = null,

	@OneToMany(fetch = FetchType.EAGER, cascade = [(CascadeType.ALL)])
	@JoinColumn(name = "group_id", referencedColumnName = "id")
	@JsonManagedReference
	var students: MutableList<User> = mutableListOf(),

	@OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
	@JoinColumn(name = "teacher_id")
	@JsonManagedReference("teacher-subjects")
	var subjects: MutableList<Subject> = mutableListOf(),
) : Serializable {
	companion object {
		private const val serialVersionUID = 68127231473987L
	}
}