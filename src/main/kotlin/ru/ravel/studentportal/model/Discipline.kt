package ru.ravel.studentportal.model

import jakarta.persistence.*


@Entity
data class Discipline(

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Long? = null,

	var name: String? = null,

	@OneToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
	var teacher: User? = null,

	@Enumerated(EnumType.STRING)
	var type: DisciplineType? = null
)