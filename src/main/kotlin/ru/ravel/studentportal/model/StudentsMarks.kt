package ru.ravel.studentportal.model

import jakarta.persistence.*


@Entity
data class StudentsMarks(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Long? = null,

	@OneToOne(cascade = [(CascadeType.ALL)], fetch = FetchType.EAGER)
	var student: User? = null,

	@OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "students_marks_id")
	var marks: MutableList<Mark> = mutableListOf()
)