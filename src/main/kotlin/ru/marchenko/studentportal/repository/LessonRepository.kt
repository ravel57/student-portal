package ru.marchenko.studentportal.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.marchenko.studentportal.model.Lesson

interface LessonRepository : JpaRepository<Lesson, Long>{
}