package ru.ravel.studentportal.model

import jakarta.persistence.*
import java.time.LocalDate


@Entity
data class Mark(

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Long? = null,

	@ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
	var subject: Subject? = null,

	var value: Int? = null,

	var date: LocalDate? = null,
)