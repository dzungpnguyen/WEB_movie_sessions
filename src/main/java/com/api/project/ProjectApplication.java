package com.api.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication(scanBasePackages={"com.api.project",
//		"com.api.project.model",
//		"com.api.project.repository",
//		"com.api.project.service",
//		"com.api.project.controller"})
@SpringBootApplication
@ComponentScan(basePackages = {"com.api.project", "com.api.project.repository"})
//@ComponentScan(basePackages = "com.api.project")
//@EnableJpaRepositories("com.api.project.repository")
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

}
