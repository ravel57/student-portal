package ru.ravel.studentportal.service

import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import ru.ravel.studentportal.dto.*
import ru.ravel.studentportal.model.*
import ru.ravel.studentportal.repository.GroupRepository
import ru.ravel.studentportal.repository.LessonRepository
import ru.ravel.studentportal.repository.SubjectRepository
import ru.ravel.studentportal.repository.UserRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Service
class StudentService(
	private val userRepository: UserRepository,
	private val subjectRepository: SubjectRepository,
	private val groupRepository: GroupRepository,
	private val lessonRepository: LessonRepository,
) {

	fun getGroups(authentication: Authentication): List<StudentGroup> {
		val principal = authentication.principal as? User
		val user = userRepository.findByEmail(principal?.username ?: "")!!
		if (user.role == Role.TEACHER || user.role == Role.ADMIN) {
			return groupRepository.findAll()
		} else {
			throw RuntimeException("user not teacher or admin")
		}
	}


	fun getStudents(groupId: GroupId): List<User> {
		return userRepository.findByGroupId(groupId.id)
			.filter { it.role == Role.STUDENT }
	}


	fun updateMark(markEntry: MarkEntry): User? {
		val student = userRepository.findById(markEntry.studentId).orElseThrow()
		val subject = subjectRepository.findById(markEntry.subjectId).orElseThrow()
		var studentMark = student?.studentsMarks?.find { it.subject?.name == subject?.name }
		val mark = Mark(
			subject = subject,
			value = markEntry.mark,
			date = LocalDate.parse(markEntry.date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
		)
		if (studentMark != null) {
			studentMark.marks.add(mark)
		} else {
			studentMark = StudentsMarks(
				student = student,
				subject = subject,
				marks = mutableListOf(mark),
			)
			student?.studentsMarks = mutableListOf(studentMark)
		}
		student?.let { userRepository.save(it) }
		return student
	}


	fun setStudentMarks(authentication: Authentication): List<StudentsMarks>? {
		val student = userRepository.findByEmail(authentication.name)
		return student?.studentsMarks
	}


	fun getStudentInfo(authentication: Authentication): User? {
		val principal = authentication.principal as? User
		return userRepository.findByEmail(principal?.username ?: "")
	}


	fun getStudentsMarksBySubjects(studentId: Long): List<MarksBySubject?> {
		val student = userRepository.findById(studentId).orElseThrow()
		val lessons = lessonRepository.findAll().filter { it.group == student.group }
		val result = lessons
			.groupBy { it.subject }
			.map { (subject, subjectLessons) ->
				val studentsMarks = student.studentsMarks.find { it.subject == subject }
				val marks = subjectLessons.map { lesson ->
					val mark = studentsMarks?.marks?.find { it.date == lesson.date }?.value
					MarkDto(
						date = lesson.date ?: error("Урок без даты"),
						mark = mark
					)
				}
				MarksBySubject(
					subject = subject ?: error("Lesson без subject"),
					marks = marks
				)
			}
		return result
	}

}