package ru.ravel.studentportal.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*


@Entity
data class Subject(

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Long? = null,

	var name: String? = null,

	@ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
	@JsonBackReference("teacher-subjects")
	var teacher: User? = null,

	@OneToMany(mappedBy = "subject", fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
	@JsonManagedReference("subject-marks")
	var studentsMarks: MutableList<StudentsMarks> = mutableListOf()
)