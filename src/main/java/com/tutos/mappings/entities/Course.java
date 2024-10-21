package com.tutos.mappings.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ManyToAny;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name="course")
public class Course {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="instructor_id")
    @ToString.Exclude
    private Instructor instructor;

    @OneToMany (cascade = {CascadeType.ALL})
    @JoinColumn(name="course_id")
    @ToString.Exclude
    private List<Review> reviews;

    @ManyToMany(fetch = FetchType.LAZY ,cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name="course_id"),
            inverseJoinColumns = @JoinColumn(name="student_id")
    )
    private List<Student> students = new ArrayList<>();

    public void add(Student student) {
        this.students.add(student);
    }

    public void add(Review review) {
        this.reviews.add(review);
    }


    public Course(String title) {
        this.title = title;
    }
}
