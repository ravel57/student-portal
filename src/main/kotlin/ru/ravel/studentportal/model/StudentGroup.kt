package ru.ravel.studentportal.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*


@Entity
data class StudentGroup(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Long? = null,

	var name: String? = null,

	@OneToMany(fetch = FetchType.EAGER, cascade = [(CascadeType.ALL)])
	@JoinColumn(name = "group_id")
	@JsonManagedReference
	var students: MutableList<User> = mutableListOf(),

	@OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
	@JoinColumn(name = "teacher_id")
	@JsonManagedReference("teacher-subjects")
	var subjects: MutableList<Subject> = mutableListOf(),
)