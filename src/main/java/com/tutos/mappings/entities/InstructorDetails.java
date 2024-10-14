package com.tutos.mappings.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name="instructor_detail")
public class InstructorDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="youtube_channel")
    private String youtube_channel;

    @Column(name="hobby")
    private String hobby;

    public InstructorDetails(String youtube_channel, String hobby) {
        this.youtube_channel = youtube_channel;
        this.hobby = hobby;
    }
}
