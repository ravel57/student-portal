package ru.ravel.studentportal.controller

import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import ru.ravel.studentportal.dto.MarkEntry
import ru.ravel.studentportal.model.StudentGroup
import ru.ravel.studentportal.service.StudentService
import ru.ravel.studentportal.service.SubjectService


@RestController
@RequestMapping("/api/v1")
class ApiController(
	private val studentService: StudentService,
	private val subjectService: SubjectService
) {

	@GetMapping("/groups")
	fun getGroups(
		authentication: Authentication,
	): ResponseEntity<Any> {
		try {
			return ResponseEntity.ok().body(studentService.getGroups(authentication))
		} catch (e: Exception) {
			return ResponseEntity.badRequest().body(e.message)
		}
	}


	@GetMapping("/students")
	fun getStudents(
		@RequestBody group: StudentGroup,
	): ResponseEntity<Any> {
		return ResponseEntity.ok().body(studentService.getStudents(/*subject*/))
	}


	@GetMapping("/subjects")
	fun getSubjects(
		authentication: Authentication
	): ResponseEntity<Any> {
		return ResponseEntity.ok().body(subjectService.getSubjects(authentication))
	}


	@PostMapping("/update-mark")
	fun updateMark(
		authentication: Authentication,
		@RequestBody markEntry: MarkEntry
	): ResponseEntity<Any> {
		return ResponseEntity.ok().body(studentService.setMark(markEntry))
	}


	@PostMapping("/students-marks")
	fun studentMarks(authentication: Authentication): ResponseEntity<Any> {
		return ResponseEntity.ok().body(studentService.studentMarks(authentication))
	}

}