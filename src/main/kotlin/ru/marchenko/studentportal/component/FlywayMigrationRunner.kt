package ru.marchenko.studentportal.component


import org.flywaydb.core.Flyway
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component


@Component
class FlywayMigrationRunner (
	private val flyway: Flyway
) : CommandLineRunner {

	override fun run(vararg args: String) {
		flyway.migrate()
	}
}