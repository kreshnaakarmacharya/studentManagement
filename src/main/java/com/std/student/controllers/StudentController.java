package com.std.student.controllers;

import com.std.student.DTO.AddStudentDTO;
import com.std.student.models.Security.CustomUserDetails;
import com.std.student.models.Student;
import com.std.student.models.User;
import com.std.student.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/add")
    public String getStudentRegister(Model model) {
        model.addAttribute("studentDTO", new AddStudentDTO());
        return "Student/RegisterStudent";
    }

    @PostMapping("/save")
    public String postStudentRegister(@ModelAttribute AddStudentDTO student, RedirectAttributes redirectAttributes) {
        studentService.addStudent(student);
        redirectAttributes.addFlashAttribute("success", "Student added successfully");
        return "redirect:/students/studentList";
    }

    @GetMapping("/studentList")
    public String getstudentList(Model model) {
        model.addAttribute("students", studentService.getAllStudent());
        return "Student/ViewAllStudent";
    }

    @GetMapping("/std/edit/{id}")
    public String editStudent(@PathVariable long id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "Student/EditStudentDetail";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable long id) {
        studentService.deleteStudentById(id);
        return "redirect:/students/studentList";
    }

    @PostMapping("/updateStudent")
    public String updateStudent(@ModelAttribute Student student) {
        studentService.updateStudent(student);
        return "redirect:/students/studentList";
    }

    @GetMapping("/view/{id}")
    public String viewStudent(@PathVariable Long id, Model model) {

        Student student = studentService.getStudentById(id);

        model.addAttribute("student", student);

        return "Student/ViewStudentDetails";
    }


    @GetMapping("/dashboard")
    public String studentDashboard(
            @AuthenticationPrincipal CustomUserDetails user,
            Model model) {

        Student student = studentService.getStudentById(user.getStudentId());

        model.addAttribute("student", student);

        return "Student/StudentDashboard";
    }


    @GetMapping("/profile")
    public String viewMyProfile(
            @AuthenticationPrincipal CustomUserDetails user,
            Model model) {

        Student student = studentService.getStudentById(user.getStudentId());

        model.addAttribute("student", student);

        return "Student/ViewProfile";
    }


    @GetMapping("/profile/edit")
    public String editMyProfile(
            @AuthenticationPrincipal CustomUserDetails user,
            Model model) {

        Student student = studentService.getStudentById(user.getStudentId());

        model.addAttribute("student", student);

        return "Student/EditProfile";
    }


    @PostMapping("/profile/update")
    public String updateMyProfile(
            @AuthenticationPrincipal CustomUserDetails user,
            @ModelAttribute Student updatedStudent) {

        studentService.updateStudentProfile(
                user.getStudentId(),
                updatedStudent
        );

        return "redirect:/students/profile";
    }

}
