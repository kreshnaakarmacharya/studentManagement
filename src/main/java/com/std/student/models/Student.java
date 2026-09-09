package com.std.student.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "std_code",  unique = true, nullable = false)
    private String stdCode;

    @NotEmpty
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Please enter a valid email address")
    @Column(name = "email")
    private String email;

    @NotEmpty
    @Column(name = "phone_number")
    private String phoneNumber;
    
    @Column(name = "address")
    private String address;

    @Column(name = "gender")
    private String gender;

    @Column(name = "course")
    private String course;

}
