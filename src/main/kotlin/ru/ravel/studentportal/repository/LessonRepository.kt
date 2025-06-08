package ru.ravel.studentportal.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.ravel.studentportal.model.Lesson

interface LessonRepository : JpaRepository<Lesson, Long>{
}