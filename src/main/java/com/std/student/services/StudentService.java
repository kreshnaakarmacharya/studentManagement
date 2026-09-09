package com.std.student.services;

import com.std.student.DTO.AddStudentDTO;
import com.std.student.models.POJO.Role;
import com.std.student.models.Student;
import com.std.student.models.User;
import com.std.student.repositories.StudentRepo;
import com.std.student.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void addStudent(AddStudentDTO studentDTO) {
        Student student = new Student();
        User user = new User();
        student.setName(studentDTO.getName());
        student.setStdCode(studentDTO.getStdCode());
        student.setEmail(studentDTO.getEmail());
        student.setPhoneNumber(studentDTO.getPhoneNumber());
        student.setAddress(studentDTO.getAddress());
        student.setGender(studentDTO.getGender());
        student.setCourse(studentDTO.getCourse());

        Student savedStudent = studentRepo.save(student);

        user.setUsername(studentDTO.getUsername());
        user.setPassword(passwordEncoder.encode(studentDTO.getPassword()));
        user.setRole(Role.STUDENT);
        user.setStudent(savedStudent);


        userRepository.save(user);
    }

    public List<Student> getAllStudent() {
        return studentRepo.findAll();
    }

    public Student getStudentById(long id) {
        return studentRepo.findById(id);
    }

    public void deleteStudentById(long id) {
        studentRepo.deleteById(id);
    }

    public void updateStudent(Student student) {
        Student std = studentRepo.findById(student.getId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        std.setName(student.getName());
        std.setEmail(student.getEmail());
        std.setPhoneNumber(student.getPhoneNumber());
        std.setAddress(student.getAddress());

        studentRepo.save(std);
    }

    public Student updateStudentProfile(Long studentId, Student updatedStudent) {

        Student existingStudent = studentRepo.findById(studentId)
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));

        existingStudent.setName(updatedStudent.getName());
        existingStudent.setAddress(updatedStudent.getAddress());
        existingStudent.setPhoneNumber(updatedStudent.getPhoneNumber());
        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setGender(updatedStudent.getGender());

        return studentRepo.save(existingStudent);
    }
}
