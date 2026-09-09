package com.std.student.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class AddStudentDTO {
    private String stdCode;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String gender;
    private String course;
    private String username;
    private String password;
}
