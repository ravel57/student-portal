package ru.marchenko.studentportal

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class StudentPortalApplication

fun main(args: Array<String>) {
	runApplication<StudentPortalApplication>(*args)
}
