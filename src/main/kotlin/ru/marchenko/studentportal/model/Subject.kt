package ru.marchenko.studentportal.model

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import java.io.Serializable


@Entity
data class Subject(

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Long? = null,

	var name: String? = null,

	@ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
	@JsonBackReference("teacher-subjects")
	var teacher: User? = null,
) : Serializable {
	companion object {
		private const val serialVersionUID = 132746514894581436L
	}
}