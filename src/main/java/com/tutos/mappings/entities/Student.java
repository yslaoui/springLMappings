package com.tutos.mappings.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="student")
@Data
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "first_name")
    String first_name;

    @Column(name = "last_name")
    String last_name;

    @Column(name = "email")
    String email;

    @ManyToMany( mappedBy = "students", fetch = FetchType.LAZY ,cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @ToString.Exclude
    List<Course> courses = new ArrayList<>();



    public void addCourse(Course course) {
        course.getStudents().add(this);
        this.courses.add(course);
    }

    public Student(String first_name, String last_name, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }
}
