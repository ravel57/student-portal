package ru.ravel.studentportal.util

import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit
import kotlin.random.Random

object DateGenerator {
	fun generateRandomDateForLastSixMonths(): ZonedDateTime {
		val now = ZonedDateTime.now()
		val sixMonthsAgo = now.minus(6, ChronoUnit.MONTHS)

		val daysBetween = ChronoUnit.DAYS.between(sixMonthsAgo, now)
		val randomDays = Random.nextLong(daysBetween)

		return sixMonthsAgo.plus(randomDays, ChronoUnit.DAYS)
			.withHour(Random.nextInt(9, 18))
			.withMinute(Random.nextInt(0, 60))
			.withSecond(0)
			.withNano(0)
	}
}