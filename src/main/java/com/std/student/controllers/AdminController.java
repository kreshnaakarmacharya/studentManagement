package com.std.student.controllers;

import com.std.student.repositories.CourseRepository;
import com.std.student.repositories.StudentRepo;
import com.std.student.repositories.TeacherRepository;
import com.std.student.services.CourseService;
import com.std.student.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {

        long totalStudents = studentRepo.count();
        Long totalCourses = courseRepository.count();
        Long totalTeacher = teacherRepository.count();

        model.addAttribute("totalStudents", totalStudents);
        model.addAttribute("students", studentService.getAllStudent());
        model.addAttribute("totalCourses", totalCourses);
        model.addAttribute("totalTeacher", totalTeacher);


        return "Admin/AdminDashboard";
    }
}
