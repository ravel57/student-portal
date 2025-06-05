package ru.ravel.studentportal.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*


@Entity
data class StudentsMarks(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Long? = null,

	@OneToOne(fetch = FetchType.EAGER, cascade = [(CascadeType.ALL)])
	@JsonBackReference("student-marks")
	var student: User? = null,

	@ManyToOne(fetch = FetchType.EAGER, cascade = [(CascadeType.ALL)])
	@JsonBackReference("subject-marks")
	var subject: Subject? = null,

	@OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER, orphanRemoval = true)
	@JsonManagedReference("marks-list")
	var marks: MutableList<Mark> = mutableListOf()
)