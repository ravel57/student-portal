package ru.marchenko.studentportal.model

import jakarta.persistence.*
import java.io.Serializable
import java.time.LocalDate


@Entity
data class Mark(

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Long? = null,

	var value: Int? = null,

	var date: LocalDate? = null,
) : Serializable {
	companion object {
		private const val serialVersionUID = 1L
	}
}