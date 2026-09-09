package com.std.student.controllers;

import com.std.student.DTO.AddTeacherDTO;
import com.std.student.models.Security.CustomUserDetails;
import com.std.student.models.Student;
import com.std.student.models.Subject;
import com.std.student.models.Teacher;
import com.std.student.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/addTeacherForm")
    public String getAddTeacher(Model model) {
        model.addAttribute("teacher", new AddTeacherDTO());
        return "Teacher/AddTeacher";
    }

    @PostMapping("/addTeacher")
    public String addTeacher(@ModelAttribute AddTeacherDTO teacher) {
        teacherService.addTeacher(teacher);
        return "redirect:/teacher/teacherList";
    }

    @GetMapping("/teacherList")
    public String getTeacherList(Model model) {
        model.addAttribute("teachers", teacherService.getAllTeacher());
        return "Teacher/ViewAllTeacher";
    }


    @GetMapping("/editTeacher/{id}")
    public String editTeacher(@PathVariable long id, Model model) {
        Teacher teacher = teacherService.getTeacherById(id);
        model.addAttribute("teacher", teacher);
        return "Teacher/EditTeacherDetails";
    }

    @GetMapping("/deleteTeacher/{id}")
    public String deleteTeacher(@PathVariable long id) {
        teacherService.deleteTeacherById(id);
        return "redirect:/teacher/teacherList";
    }

    @PostMapping("/updateTeacher")
    public String updateTeacher(@ModelAttribute Teacher teacher) {
        teacherService.updateTeacher(teacher);
        return "redirect:/teacher/teacherList";
    }

    @GetMapping("/view/{id}")
    public String viewTeacher(@PathVariable Long id, Model model) {

        Teacher teacher = teacherService.getTeacherById(id);

        model.addAttribute("teacher", teacher);

        return "Teacher/ViewTeacherDetails";
    }

    @GetMapping("/{teacherId}/subjects")
    public String showTeacherSubjects(
            @PathVariable Long teacherId,
            Model model) {

        Teacher teacher =
                teacherService.getTeacherWithSubjects(teacherId);

        List<Subject> subjects =
                teacherService.getAllSubjects();

        model.addAttribute("teacher", teacher);
        model.addAttribute("subjects", subjects);

        return "Teacher/TeacherSubjects";
    }

    @PostMapping("/{teacherId}/subjects/add")
    public String addSubject(
            @PathVariable Long teacherId,
            @RequestParam Long subjectId) {

        teacherService.addSubjectToTeacher(
                teacherId,
                subjectId
        );

        return "redirect:/teacher/" + teacherId + "/subjects";
    }

    @GetMapping("/{teacherId}/subjects/delete/{subjectId}")
    public String deleteSubject(
            @PathVariable Long teacherId,
            @PathVariable Long subjectId) {

        teacherService.removeSubjectFromTeacher(
                teacherId,
                subjectId
        );

        return "redirect:/teacher/" + teacherId + "/subjects";
    }

    @GetMapping("/dashboard")
    public String dashboard(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            Model model) {

        Long teacherId = userDetails.getTeacherId();

        Teacher teacher = teacherService.getTeacherById(teacherId);

        model.addAttribute("teacher", teacher);

        return "Teacher/TeacherDashboard";
    }

    @GetMapping("/profile")
    public String viewProfile(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            Model model) {

        Long teacherId = userDetails.getTeacherId();

        Teacher teacher = teacherService.getTeacherById(teacherId);

        model.addAttribute("teacher", teacher);

        return "Teacher/ViewProfile";
    }

    @GetMapping("/profile/edit")
    public String editProfile(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            Model model) {

        Long teacherId = userDetails.getTeacherId();

        Teacher teacher = teacherService.getTeacherById(teacherId);

        model.addAttribute("teacher", teacher);

        return "Teacher/EditProfile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @ModelAttribute Teacher teacher) {

        Long teacherId = userDetails.getTeacherId();

        teacherService.updateTeacherProfile(teacherId, teacher);

        return "redirect:/teacher/profile";
    }

    @GetMapping("/subjects")
    public String mySubjects(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            Model model) {

        Long teacherId = userDetails.getTeacherId();

        List<Subject> subjects =
                teacherService.getSubjectsByTeacherId(teacherId);

        model.addAttribute("subjects", subjects);

        return "Teacher/MySubjects";
    }
}

