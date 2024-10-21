package com.tutos.mappings.dao;

import com.tutos.mappings.entities.Course;
import com.tutos.mappings.entities.Instructor;
import com.tutos.mappings.entities.InstructorDetails;
import com.tutos.mappings.entities.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppDaoImpl implements AppDao {


    EntityManager entityManager;

    public AppDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        this.entityManager.persist(instructor);
    }

    @Override
    public Instructor findById(int theId) {
        return this.entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Instructor instructor = findById(id);
        for (Course course: instructor.getCourses()) {
            course.setInstructor(null);
        }
        this.entityManager.remove(instructor);
    }

    @Override
    public InstructorDetails findDetailsById(int id) {
        return this.entityManager.find(InstructorDetails.class, id);
    }

    @Override
    @Transactional
    public void deleteDetailsById(int id) {
        InstructorDetails details = findDetailsById(id);
        details.getInstructor().setInstructorDetails(null);
        this.entityManager.remove(details);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {
        TypedQuery<Course> query = this.entityManager.
                createQuery("from Course where instructor.id = :data", Course.class);
        query.setParameter("data", id);
        return query.getResultList();
    }

    @Override
    public Instructor findByIdJoinFetch(int id) {
        TypedQuery<Instructor> query = this.entityManager
                .createQuery("select i from Instructor i " +
                        "JOIN FETCH i.courses " +
                        "JOIN FETCH i.instructorDetails" +
                        "where i.id = :data ", Instructor.class);
        query.setParameter("data", id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        this.entityManager.merge(instructor);
    }

    @Override
    public Course findByCourseId(int id) {
        Course course = this.entityManager.find(Course.class, id);
        return course;
    }

    @Override
    @Transactional
    public void update(Course course) {
        this.entityManager.merge(course);
    }

    @Override
    @Transactional
    public void save(Course course) {
        this.entityManager.persist(course);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
        Course course = findByCourseId(id);
        this.entityManager.remove(course);
    }

    @Override
    public Course findCourseAndStudentsByCourseId(int id) {
    TypedQuery<Course> query = this.entityManager
                .createQuery("select c from Course c " +
                        "join fetch c.students " +
                        "where c.id = :data", Course.class);
        query.setParameter("data", id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void save(Student student) {
        this.entityManager.persist(student);
    }

    @Override
    public Student findStudentAndCoursesByStudentId(int id) {
        TypedQuery<Student> query = this.entityManager
                .createQuery("select s from Student s " +
                        "join fetch s.courses " +
                        "where s.id= :data", Student.class);
        query.setParameter("data", id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void updateStudent(Student student) {
        this.entityManager.merge(student);
    }

    @Override
    @Transactional
    public void deleteStudentById(int id) {
        Student student = this.entityManager.find(Student.class, id);
        for (Course course: student.getCourses()) {
            course.getStudents().remove(student);
        }
        this.entityManager.remove(student);
    }


}
