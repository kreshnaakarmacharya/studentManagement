package com.std.student.controllers;

import com.std.student.models.Course;
import com.std.student.models.Subject;
import com.std.student.services.CourseService;
import com.std.student.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/subjects")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/addSubjectForm")
    public String getAddSubject(Model model, @RequestParam Long courseId) {
        model.addAttribute("courseId", courseId);
        model.addAttribute("subject", new Subject());
        return "Subjects/AddSubject";
    }

    @PostMapping("/addSubject")
    public String addSubject(@RequestParam Long courseId, @ModelAttribute Subject subject) {
        subjectService.addSubject(courseId, subject);
        return "redirect:/subjects/subjectList?courseId=" + courseId;
    }

    @GetMapping("/subjectList")
    public String getSubjectList(@RequestParam Long courseId, Model model) {
        List<Subject> subjects = subjectService.getSubjectByCourseId(courseId);

        Course course = courseService.getCourseById(courseId);

        model.addAttribute("subjects", subjects);
        model.addAttribute("courseId", courseId);
        model.addAttribute("course", course);

        return "Subjects/ViewAllSubject";
    }

    @GetMapping("/editSubject/{id}")
    public String editSubject(@PathVariable long id, Model model) {
        Subject subject = subjectService.getSubjectById(id);
        model.addAttribute("subject", subject);
        return "Subjects/EditSubject";
    }

    @GetMapping("/deleteSubject/{id}")
    public String deleteSubject(@PathVariable long id) {
        Subject subject = subjectService.getSubjectById(id);

        Long courseId = subject.getCourse().getId();

        subjectService.deleteSubjectById(id);

        return "redirect:/subjects/subjectList?courseId=" + courseId;
    }

    @PostMapping("/updateSubject")
    public String updateSubject(@ModelAttribute Subject subject) {

        subjectService.updateSubject(subject);

        Long courseId = subject.getCourse().getId();

        return "redirect:/subjects/subjectList?courseId=" + courseId;
    }


}
