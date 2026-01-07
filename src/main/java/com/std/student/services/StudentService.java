package com.std.student.services;

import com.std.student.models.Student;
import com.std.student.repositories.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;
     public void addStudent(Student student){
         studentRepo.save(student);
     }

     public List<Student> getAllStudent(){
        return  studentRepo.findAll();
     }

     public Student getStudentById(long id){
         return studentRepo.findById(id);
     }

     public void deleteStudentById(long id){
         studentRepo.deleteById(id);
     }

     public void updateStudent(Student student){
         studentRepo.save(student);
     }

}
