package ru.ravel.studentportal.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.ravel.studentportal.model.StudentsMarks

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param


interface StudentsMarksRepository : JpaRepository<StudentsMarks, Long> {

	@Query("SELECT sm FROM StudentsMarks sm WHERE sm.student.id = :studentId AND sm.subject.id = :subjectId")
	fun findByStudentIdAndSubjectId(
		@Param("studentId") studentId: Long,
		@Param("subjectId") subjectId: Long
	): StudentsMarks?

	fun existsByStudentIdAndSubjectId(studentId: Long, subjectId: Long): Boolean

}