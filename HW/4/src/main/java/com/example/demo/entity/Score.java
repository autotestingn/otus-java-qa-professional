package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "scores")
@Setter
@Getter
@ToString
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer score;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="user_id")

    private User user;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="course_id")
    private Course course;

}
