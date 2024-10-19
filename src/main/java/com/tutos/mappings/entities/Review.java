package com.tutos.mappings.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="review")
public class Review {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    @Id
    int id;

    @Column(name="comment")
    String comment;

    public Review(String comment) {
        this.comment = comment;
    }
}
