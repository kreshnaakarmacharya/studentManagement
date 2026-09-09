package com.std.student.repositories;

import com.std.student.models.Subject;
import com.std.student.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findByEmail(String email);

    @Query(value = """
            SELECT s.*
            FROM subject s
            INNER JOIN teacher_subject ts
                ON s.id = ts.subject_id
            WHERE ts.teacher_id = :teacherId
            """, nativeQuery = true)
    List<Subject> findSubjectsByTeacherId(
            @Param("teacherId") Long teacherId);
}
