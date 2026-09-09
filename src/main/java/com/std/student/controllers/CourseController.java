package com.std.student.controllers;

import com.std.student.DTO.AddTeacherDTO;
import com.std.student.models.Course;
import com.std.student.models.Teacher;
import com.std.student.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/addCourseForm")
    public String getAddCourse(Model model) {
        model.addAttribute("course", new Course());
        return "Courses/AddCourse";
    }

    @PostMapping("/addCourse")
    public String addCourse(@ModelAttribute Course course) {
        courseService.addCourse(course);
        return "redirect:/courses/courseList";
    }

    @GetMapping("/courseList")
    public String getCourseList(Model model) {
        model.addAttribute("courses", courseService.getAllCourse());
        return "Courses/ViewAllCourse";
    }

    @GetMapping("/editCourse/{id}")
    public String editCourse(@PathVariable long id, Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("course", course);
        return "Courses/EditCourse";
    }

    @GetMapping("/deleteCourse/{id}")
    public String deleteCourse(@PathVariable long id) {
        courseService.deleteCourseById(id);
        return "redirect:/courses/courseList";
    }

    @PostMapping("/updateCourse")
    public String updateCourse(@ModelAttribute Course course) {
        courseService.updateCourse(course);
        return "redirect:/courses/courseList";
    }

    @GetMapping("/view/{id}")
    public String viewTeacher(@PathVariable Long id, Model model) {

        Course course = courseService.getCourseById(id);

        model.addAttribute("course", course);

        return "Courses/ViewCourseDetails";
    }

}
