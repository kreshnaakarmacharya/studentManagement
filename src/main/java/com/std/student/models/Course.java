package com.std.student.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "course")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course_code", nullable = false, unique = true)
    private String courseCode;

    @Column(name = "course_name", nullable = false)
    private String courseName;

    @Column(name = "duration")
    private String duration;

    @Column(name = "total_semesters")
    private Integer totalSemesters;

    @Column(name = "status")
    private String status;
}
