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

import java.util.List;
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
//			findInstructorWithCoursesById(dao);
//			findInstructorWithCoursesByIdQueryCourse(dao);
//			findInstructorWithCoursesByIdQueryJoinFetch(dao);
//			updateInstructorFirstName(dao);
//			updateCourseTitle(dao);

		};
	}

	private void updateCourseTitle(AppDao dao) {
		int id = 11;
		Course course = dao.findByCourseId(id);
		course.setTitle("salsa");
		dao.update(course);
	}

	private void updateInstructorFirstName(AppDao dao) {
		int id = 1;
		Instructor instructor = dao.findById(id);
		instructor.setFirstName("Angelica");
		dao.update(instructor);
	}

	public void findInstructorWithCoursesByIdQueryJoinFetch(AppDao dao) {
		int id = 1;
		Instructor instructor = dao.findByIdJoinFetch(id);
		System.out.println(instructor.getCourses());
	}

	public void findInstructorWithCoursesByIdQueryCourse(AppDao dao) {
		int id = 1;
		Instructor instructor = dao.findById(id);
		List<Course> courses = dao.findCoursesByInstructorId(id);
		instructor.setCourses(courses);
		System.out.println(instructor.getCourses());
	}

	public void findInstructorWithCoursesById(AppDao dao) {
		int id = 1;
		Instructor instructor = dao.findById(id);
//		System.out.println(instructor.getInstructorDetails());
		System.out.println(instructor.getCourses());
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
		dao.deleteById(theId);
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
		InstructorDetails details = new InstructorDetails("iglesias.youtube.com", "femmes");
		dao.save(instructor);
	}
}
