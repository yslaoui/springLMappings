package com.tutos.mappings;

import com.tutos.mappings.dao.AppDao;
import com.tutos.mappings.entities.*;
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
//			deleteInstructor(dao);
//			findDetails(dao);
//			removeDetails(dao);
//			createInstructorWithCourse(dao);
//			findInstructorWithCoursesById(dao);
//			findInstructorWithCoursesByIdQueryCourse(dao);
//			findInstructorWithCoursesByIdQueryJoinFetch(dao);
//			updateInstructorFirstName(dao);
//			updateCourseTitle(dao);
//			createCourse(dao);
//			deleteCourse(dao);
//			CreateCourseAndStudents(dao);
//			findCourseWithStudents(dao);
//			createStudentAndCourses(dao);
//			findStudentAndCourses(dao);
//			addCoursesToStudent(dao);
			deleteStudent(dao);
		};
	}

	private void deleteStudent(AppDao dao) {
		int id = 2;
		dao.deleteStudentById(id);
	}

	private void addCoursesToStudent(AppDao dao) {
		int id = 2;
		Student student = dao.findStudentAndCoursesByStudentId(id);
		System.out.println("student " + student.getFirst_name());
		student.addCourse(new Course("french literature"));
		student.addCourse(dao.findCourseAndStudentsByCourseId(12));
		dao.updateStudent(student);
	}

	private void findStudentAndCourses(AppDao dao) {
		int id = 3;
		Student student = dao.findStudentAndCoursesByStudentId(id);
		System.out.println("found student " + student.getFirst_name() + student.getLast_name());
		for(Course course: student.getCourses()) {
			System.out.println("follows course " + course.getTitle());
		}
	}


	private void createStudentAndCourses(AppDao dao) {
		Student student = new Student("Elise", "Mozart", "elise.mozart@gmail.com");
		student.addCourse(new Course("piano"));
		student.addCourse(new Course("violin"));
		dao.save(student);
	}

	private void findCourseWithStudents(AppDao dao) {
		int id = 10;
		Course course = dao.findCourseAndStudentsByCourseId(id);
		System.out.println("Course is " + course.getTitle());
		for (Student student: course.getStudents()) {
			System.out.println("has the student " + student);
		}

	}

//	private void findStudentWithCourses(AppDao dao) {
//		int id = 2;
//		Student student = dao.findStudentAndCoursesByStudentId(id);
//		System.out.println("Student is " + student.getFirst_name() + " " + student.getLast_name());
//		for (Course course: student.getCourses()) {
//			System.out.println("took the course " + course);
//		}
//	}

	private void CreateCourseAndStudents(AppDao dao) {
		Course course = new Course("Mathematics");
		Student student1 = new Student("Anaelle", "Guilbon", "annaelle.guilbon@gmail.com");
		Student student2 = new Student("Cecile", "camus", "cecile.camus@gmail.com");
		course.add(student1);
		course.add(student2);
		dao.save(course);

	}


	private void deleteCourse(AppDao dao) {
		int id = 12;
		dao.deleteCourseById(id);
	}

	private void createCourse(AppDao dao) {
		Course course = new Course("SVT");
		course.add(new Review("I liked the course"));
		course.add(new Review("The course is meuh"));
		course.add(new Review("too slow"));
		dao.save(course);
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
