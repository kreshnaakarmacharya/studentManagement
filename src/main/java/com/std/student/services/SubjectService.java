package com.std.student.services;

import com.std.student.models.Course;
import com.std.student.models.Subject;
import com.std.student.repositories.CourseRepository;
import com.std.student.repositories.SubjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Transactional
    public void addSubject(Long courseId, Subject subject) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        subject.setCourse(course);

        subjectRepository.save(subject);
    }

    public List<Subject> getSubjectByCourseId(Long courseId) {
        return subjectRepository.findByCourseId(courseId);
    }

    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Subject not found"));
    }

    public void deleteSubjectById(Long id) {
        subjectRepository.deleteById(id);
    }

    @Transactional
    public void updateSubject(Subject subject) {
        Subject existSubject = subjectRepository.findById(subject.getId()).get();
        existSubject.setSubjectCode(subject.getSubjectCode());
        existSubject.setSubjectName(subject.getSubjectName());
        existSubject.setSemesterNumber(subject.getSemesterNumber());
        subjectRepository.save(existSubject);
    }
}
