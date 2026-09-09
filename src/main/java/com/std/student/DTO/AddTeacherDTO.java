package com.std.student.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class AddTeacherDTO {
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String gender;
    private String qualification;
    private String username;
    private String password;
}
