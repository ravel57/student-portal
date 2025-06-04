package ru.ravel.studentportal.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
internal class MvcConfig : WebMvcConfigurer {
	override fun addViewControllers(registry: ViewControllerRegistry) {
		registry.addViewController("/login").setViewName("index")
		registry.addViewController("/portal").setViewName("index")
	}


	override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
		registry.addResourceHandler("/js/**")
			.addResourceLocations("classpath:/static/js/")
		registry.addResourceHandler("/css/**")
			.addResourceLocations("classpath:/static/css/")
		registry.addResourceHandler("/favicon.ico")
			.addResourceLocations("classpath:/static/favicon.ico")
	}
}