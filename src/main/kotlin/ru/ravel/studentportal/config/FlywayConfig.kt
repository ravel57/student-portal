package ru.ravel.studentportal.config

import org.flywaydb.core.Flyway
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DriverManagerDataSource
import javax.sql.DataSource


@Configuration
class FlywayConfig {
	@Value("\${spring.datasource.url}")
	private val url: String? = null

	@Value("\${spring.datasource.username}")
	private val username: String? = null

	@Value("\${spring.datasource.password}")
	private val password: String? = null

	@Value("\${spring.flyway.locations}")
	private val locations: String? = null

	@Bean
	fun flyway(dataSource: DataSource?): Flyway {
		return Flyway.configure()
			.dataSource(dataSource)
			.locations(locations)
			.baselineOnMigrate(true)
			.load()
	}

	@Bean
	fun dataSource(): DataSource {
		val dataSource = DriverManagerDataSource()
		dataSource.setDriverClassName("org.postgresql.Driver")
		dataSource.url = url
		dataSource.username = username
		dataSource.password = password
		return dataSource
	}
}