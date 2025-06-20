package ru.marchenko.studentportal.controller

import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import ru.marchenko.studentportal.dto.*
import ru.marchenko.studentportal.service.LessonService
import ru.marchenko.studentportal.service.StudentService
import ru.marchenko.studentportal.service.SubjectService
import ru.marchenko.studentportal.service.UserService


@RestController
@RequestMapping("/api/v1")
class ApiController(
	private val studentService: StudentService,
	private val subjectService: SubjectService,
	private val lessonService: LessonService,
	private val userService: UserService
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

	@GetMapping("/me")
	fun getStudentInfo(
		authentication: Authentication,
	): ResponseEntity<Any> {
		return try {
			ResponseEntity.ok().body(studentService.getStudentInfo(authentication))
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


	@PostMapping("/update-mark")
	fun updateMark(
		authentication: Authentication,
		@RequestBody markEntry: MarkEntry
	): ResponseEntity<Any> {
		return try {
			ResponseEntity.ok().body(studentService.updateMark(markEntry))
		} catch (e: Exception) {
			ResponseEntity.badRequest().body(e.message)
		}
	}


	@PostMapping("/students-marks")
	fun studentMarks(authentication: Authentication): ResponseEntity<Any> {
		return ResponseEntity.ok().body(studentService.setStudentMarks(authentication))
	}


	@PostMapping("/lesson")
	fun createLesson(
		authentication: Authentication,
		@RequestBody lesson: LessonDto
	): ResponseEntity<Any> {
		return ResponseEntity.ok().body(lessonService.createLesson(lesson))
	}


	@GetMapping("/lessons")
	fun getLessons(): ResponseEntity<Any> {
		return ResponseEntity.ok().body(lessonService.getLessons())
	}


	@PostMapping("/lessons-by")
	fun getLessonsByGroup(
		@RequestBody lessonBy: LessonBy
	): ResponseEntity<Any> {
		return ResponseEntity.ok().body(lessonService.getLessonsBy(lessonBy))
	}


	@GetMapping("/students-marks-by-subjects")
	fun getStudentsMarksBySubjects(@RequestParam studentId: Long): ResponseEntity<Any> {
		return ResponseEntity.ok().body(studentService.getStudentsMarksBySubjects(studentId))
	}


	@PostMapping("/user")
	fun createNewUser(
		@RequestBody newUserDto: NewUserDto
	): ResponseEntity<Any> {
		return ResponseEntity.ok().body(userService.createNewUser(newUserDto))
	}

}