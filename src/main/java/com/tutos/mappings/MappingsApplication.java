package com.tutos.mappings;

import com.tutos.mappings.dao.AppDao;
import com.tutos.mappings.entities.Instructor;
import com.tutos.mappings.entities.InstructorDetails;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.logging.Logger;

@SpringBootApplication
public class MappingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MappingsApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner(AppDao dao) {
		return runner -> {
			createInstructor(dao);
		};
	}

	public void createInstructor(AppDao dao) {
		Instructor instructor = new Instructor("emmanuel", "vans", "ammanuel.vans@gmail.com");
		InstructorDetails details = new InstructorDetails("techLeadYoutubeChannel", "surf");
		instructor.setInstructorDetails(details);
		System.out.println("saving " + instructor.getFirstName());
		dao.save(instructor);
	}
}
