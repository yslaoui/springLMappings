package com.tutos.mappings;

import com.tutos.mappings.dao.AppDao;
import com.tutos.mappings.entities.Course;
import com.tutos.mappings.entities.Instructor;
import com.tutos.mappings.entities.InstructorDetails;
import jakarta.persistence.criteria.CriteriaBuilder;
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
//			createInstructor(dao);
//			findInstructor(dao);
			deleteInstructor(dao);
//			findDetails(dao);
//			removeDetails(dao);
//			createInstructorWithCourse(dao);
		};
	}

	public void createInstructor(AppDao dao) {
		Instructor instructor = new Instructor("emmanuel", "vans", "ammanuel.vans@gmail.com");
		InstructorDetails details = new InstructorDetails("techLeadYoutubeChannel", "surf");
		instructor.setInstructorDetails(details);
		System.out.println("saving " + instructor.getFirstName());
		dao.save(instructor);
	}

	public void findInstructor(AppDao dao) {
		int theId = 1;
		Instructor instructor = dao.findById(theId);
		System.out.println(instructor);
		System.out.println(instructor.getInstructorDetails());
	}

	public void deleteInstructor(AppDao dao) {
		int theId =1;
		dao.deleteById(1);
	}

	public void findDetails(AppDao dao) {
		int theId = 2;
		InstructorDetails details = dao.findDetailsById(theId);
		System.out.println(details);
		System.out.println(details.getInstructor());
	}

	public void removeDetails(AppDao dao) {
		int theId = 3;
		dao.deleteDetailsById(theId);
	}

	public void createInstructorWithCourse(AppDao dao) {
		Instructor instructor = new Instructor("Enrique", "Iglesias", "enrique.iglesias@gmail.com");
		Course music = new Course("music");
		Course tango = new Course("tango");
		instructor.add(music);
		instructor.add(tango);
		dao.save(instructor);
	}
}
