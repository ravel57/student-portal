package ru.ravel.studentportal.controller

import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import ru.ravel.studentportal.dto.GroupId
import ru.ravel.studentportal.dto.MarkEntry
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
		return try {
			ResponseEntity.ok().body(studentService.getGroups(authentication))
		} catch (e: Exception) {
			ResponseEntity.badRequest().body(e.message)
		}
	}


	@PostMapping("/students")
	fun getStudents(
		@RequestBody groupId: GroupId,
	): ResponseEntity<Any> {
		return ResponseEntity.ok().body(studentService.getStudents(groupId))
	}


	@GetMapping("/subjects")
	fun getSubjects(
		authentication: Authentication
	): ResponseEntity<Any> {
		return ResponseEntity.ok().body(subjectService.getSubjects(authentication))
	}


	@GetMapping("/students-marks")
	fun getStudentsMarks(
		authentication: Authentication,
		@RequestParam groupId: String,
	): ResponseEntity<Any> {
		return ResponseEntity.ok().body(studentService.getStudentsMarks(groupId))
	}


	@PostMapping("/update-mark")
	fun updateMark(
		authentication: Authentication,
		@RequestBody markEntry: MarkEntry
	): ResponseEntity<Any> {
		return try {
			ResponseEntity.ok().body(studentService.setMark(markEntry))
		} catch (e: Exception) {
			ResponseEntity.badRequest().body(e.message)
		}
	}


	@PostMapping("/students-marks")
	fun studentMarks(authentication: Authentication): ResponseEntity<Any> {
		return ResponseEntity.ok().body(studentService.setStudentMarks(authentication))
	}

}