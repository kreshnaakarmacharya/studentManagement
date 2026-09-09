package com.std.student.services;

import com.std.student.DTO.AddTeacherDTO;
import com.std.student.models.POJO.Role;
import com.std.student.models.Student;
import com.std.student.models.Subject;
import com.std.student.models.Teacher;
import com.std.student.models.User;
import com.std.student.repositories.SubjectRepository;
import com.std.student.repositories.TeacherRepository;
import com.std.student.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SubjectRepository subjectRepository;

    @Transactional
    public void addTeacher(AddTeacherDTO teacher) {
        Optional<Teacher> existTeacher = teacherRepository.findByEmail(teacher.getEmail());

        if (existTeacher.isPresent()) {
            throw new RuntimeException("Teacher with this email already exists");
        }

        Teacher newTeacher = new Teacher();
        newTeacher.setName(teacher.getName());
        newTeacher.setEmail(teacher.getEmail());
        newTeacher.setPhoneNumber(teacher.getPhoneNumber());
        newTeacher.setAddress(teacher.getAddress());
        newTeacher.setGender(teacher.getGender());
        newTeacher.setQualification(teacher.getQualification());

        Teacher savedTeacher = teacherRepository.save(newTeacher);

        User user = new User();
        user.setUsername(teacher.getUsername());
        user.setPassword(passwordEncoder.encode(teacher.getPassword()));
        user.setRole(Role.TEACHER);
        user.setTeacher(savedTeacher);

        userRepository.save(user);
    }

    public List<Teacher> getAllTeacher() {
        return teacherRepository.findAll();
    }

    public void deleteTeacherById(long id) {
        teacherRepository.deleteById(id);
    }

    public void updateTeacher(Teacher teacher) {
        Teacher existTeacher = teacherRepository.findById(teacher.getId()).get();
        existTeacher.setName(teacher.getName());
        existTeacher.setEmail(teacher.getEmail());
        existTeacher.setPhoneNumber(teacher.getPhoneNumber());
        existTeacher.setAddress(teacher.getAddress());
        existTeacher.setQualification(teacher.getQualification());

        teacherRepository.save(existTeacher);
    }

    // Get teacher with all assigned subjects
    public Teacher getTeacherWithSubjects(Long teacherId) {

        return teacherRepository.findById(teacherId)
                .orElseThrow(() ->
                        new RuntimeException("Teacher not found"));
    }


    // Get all subjects
    public List<Subject> getAllSubjects() {

        return subjectRepository.findAll();
    }

    public void addSubjectToTeacher(Long teacherId, Long subjectId) {

        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() ->
                        new RuntimeException("Teacher not found"));

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() ->
                        new RuntimeException("Subject not found"));

        // Prevent duplicate subject
        if (teacher.getSubjects().contains(subject)) {
            throw new RuntimeException(
                    "Subject is already assigned to this teacher");
        }

        teacher.getSubjects().add(subject);

        teacherRepository.save(teacher);
    }

    public void removeSubjectFromTeacher(Long teacherId,
                                         Long subjectId) {

        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() ->
                        new RuntimeException("Teacher not found"));

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() ->
                        new RuntimeException("Subject not found"));

        teacher.getSubjects().remove(subject);

        teacherRepository.save(teacher);
    }

    public Teacher getTeacherById(Long id) {

        return teacherRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Teacher not found with id: " + id));
    }

    // Update teacher's own profile
    public void updateTeacherProfile(Long teacherId, Teacher updatedTeacher) {

        Teacher existingTeacher = teacherRepository.findById(teacherId)
                .orElseThrow(() ->
                        new RuntimeException("Teacher not found with id: " + teacherId));

        existingTeacher.setName(updatedTeacher.getName());
        existingTeacher.setAddress(updatedTeacher.getAddress());
        existingTeacher.setPhoneNumber(updatedTeacher.getPhoneNumber());
        existingTeacher.setEmail(updatedTeacher.getEmail());
        existingTeacher.setGender(updatedTeacher.getGender());

        teacherRepository.save(existingTeacher);
    }

    public List<Subject> getSubjectsByTeacherId(Long teacherId) {
        return teacherRepository.findSubjectsByTeacherId(teacherId);
    }
}
