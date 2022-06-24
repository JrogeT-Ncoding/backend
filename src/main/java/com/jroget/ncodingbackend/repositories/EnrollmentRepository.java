package com.jroget.ncodingbackend.repositories;

import com.jroget.ncodingbackend.models.Enrollment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends CrudRepository<Enrollment, Integer> {
    List<Enrollment> findByUser_Id(int userId);
    List<Enrollment> findByCourse_Id(int courseId);
    Optional<Enrollment> findByUser_IdAndCourse_Id(int userId, int courseId);
}
