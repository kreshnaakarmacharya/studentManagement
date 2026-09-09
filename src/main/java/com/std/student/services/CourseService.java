package com.std.student.services;

import com.std.student.models.Course;
import com.std.student.models.Teacher;
import com.std.student.repositories.CourseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Transactional
    public void addCourse(Course course) {
        courseRepository.save(course);
    }

    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
    }

    public void deleteCourseById(Long id) {
        courseRepository.deleteById(id);
    }

    @Transactional
    public void updateCourse(Course course) {
        Course existCourse = courseRepository.findById(course.getId()).get();
        existCourse.setCourseCode(course.getCourseCode());
        existCourse.setCourseName(course.getCourseName());
        existCourse.setDuration(course.getDuration());
        existCourse.setTotalSemesters(course.getTotalSemesters());
        existCourse.setStatus(course.getStatus());

        courseRepository.save(existCourse);
    }
}
