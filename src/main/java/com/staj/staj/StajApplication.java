package com.staj.staj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StajApplication {
	public static void main(String[] args) {
		SpringApplication.run(StajApplication.class, args);
	}

	/*
	@Bean
	CommandLineRunner initDatabase(RoleServiceImpl roleService){
		return  args -> {
			Role role=new Role();
			role.setName("TEACHER");
			role.setDescription("Teacher role");

			roleService.addRole(role);

			Role role1 = new Role();
			role1.setName("STUDENT");
			role1.setDescription("Student role");

			roleService.addRole(role1);
		};
	}

	 */

	/*
	@Bean
	CommandLineRunner initDatabase(CourseServiceImpl courseService){
		return  args -> {
			Course course1 = new Course();
			course1.setCourse_name("Matematik");
			course1.setDescription("Matematik dersi açıklaması");

			Course course2 = new Course();
			course2.setCourse_name("Fizik");
			course2.setDescription("Fizik dersi açıklaması");

			// Dersleri veritabanına kaydet
			courseService.add(course1);
			courseService.add(course2);
		};
	}

	 */





}
