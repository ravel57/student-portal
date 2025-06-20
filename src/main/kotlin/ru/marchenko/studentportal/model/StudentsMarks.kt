package ru.marchenko.studentportal.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import java.io.Serializable


@Entity
data class StudentsMarks(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Long? = null,

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "student_id")
	@JsonBackReference("student-marks")
	var student: User? = null,

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "subject_id")
	@JsonBackReference("subject-marks")
	var subject: Subject? = null,

	@OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER, orphanRemoval = true)
	@JsonManagedReference("marks-list")
	@JoinColumn(name = "students_marks_id")
	var marks: MutableList<Mark> = mutableListOf()
) : Serializable {
	companion object {
		private const val serialVersionUID = 4561278914621L
	}
}