package ru.marchenko.studentportal.service

import org.springframework.stereotype.Service
import ru.marchenko.studentportal.dto.LessonBy
import ru.marchenko.studentportal.dto.LessonDto
import ru.marchenko.studentportal.model.Lesson
import ru.marchenko.studentportal.repository.GroupRepository
import ru.marchenko.studentportal.repository.LessonRepository
import ru.marchenko.studentportal.repository.SubjectRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Service
class LessonService(
	val lessonRepository: LessonRepository,
	private val subjectRepository: SubjectRepository,
	private val groupRepository: GroupRepository
) {

	fun createLesson(lessonDto: LessonDto): Lesson {
		val lesson = Lesson(
			subject = subjectRepository.findById(lessonDto.subjectId).orElse(null),
			group = groupRepository.findById(lessonDto.groupId).orElse(null),
			date = LocalDate.parse(lessonDto.date, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
		)
		return lessonRepository.save(lesson)
	}

	fun getLessons(): List<Lesson> {
		return lessonRepository.findAll()
	}

	fun getLessonsBy(lessonBy: LessonBy): List<Lesson> {
		return lessonRepository.findAll()
			.filter { it.group?.id == lessonBy.groupId }
			.filter { it.subject?.id == lessonBy.subjectId }
			.sortedBy { it.date }
	}

}