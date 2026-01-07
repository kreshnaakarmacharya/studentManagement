package com.std.student.controllers;

import com.std.student.models.Student;
import com.std.student.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;


    @GetMapping("/home")
    public String homePage(){
        return "Student/Student";
    }
    @GetMapping("/studentRegister")
    public  String getStudentRegister(){
        return "Student/RegisterStudent";
    }
    @PostMapping("/studentregister")
    public String postStudentRegister(@ModelAttribute Student student){
        studentService.addStudent(student);
        return "Student/Student";
    }
    @GetMapping("/studentList")
    public String getstudentList(Model model){
        model.addAttribute("student" ,studentService.getAllStudent());
        return "Student/ViewAllStudent";
    }
    @GetMapping("/std/edit")
    public String editStudent(@RequestParam("id") long id,Model model){
        Student student = studentService.getStudentById(id);
        model.addAttribute("stdedit",student);
        return "Student/EditStudentDetail";
    }

    @GetMapping("/std/delete")
    public String deleteStudent(@RequestParam("id") long id){
        studentService.deleteStudentById(id);
        return "redirect:/studentList";
    }

    @PostMapping("/std/update")
    public String updateStudent(@ModelAttribute Student student){
        studentService.updateStudent(student);
        return "redirect:/studentList";
    }


}
