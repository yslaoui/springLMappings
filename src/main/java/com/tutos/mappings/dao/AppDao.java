package com.tutos.mappings.dao;

import com.tutos.mappings.entities.Course;
import com.tutos.mappings.entities.Instructor;
import com.tutos.mappings.entities.InstructorDetails;
import com.tutos.mappings.entities.Student;

import java.util.List;

public interface AppDao {
    public void save(Instructor instructor);
    public Instructor findById(int id);
    public void deleteById(int id);
    public InstructorDetails findDetailsById(int id);
    public void deleteDetailsById(int id);
    public List<Course> findCoursesByInstructorId(int id);
    public Instructor findByIdJoinFetch(int id);
    public void update(Instructor instructor);
    public Course findByCourseId(int id);
    public void update(Course course);
    public void save(Course course);
    public void deleteCourseById(int id);
    public Course findCourseAndStudentsByCourseId(int id);
    public void save(Student student);
    public Student findStudentAndCoursesByStudentId(int id);
    public void updateStudent(Student student);
    public void deleteStudentById(int id);
}
