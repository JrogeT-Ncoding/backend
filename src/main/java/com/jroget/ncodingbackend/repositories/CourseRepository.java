package com.jroget.ncodingbackend.repositories;

import com.jroget.ncodingbackend.models.Category;
import com.jroget.ncodingbackend.models.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, Integer> {

    List<Course> findByCategory(Category category);
    List<Course> findByAvailableCapacityIsGreaterThan(int availableCapacity);

    @Query("select course from Course course where course.availableCapacity <> course.maxCapacity")
    List<Course> findAvailableCapacityIsNotEqualMaxCapacity();

    @Query("select course " +
            "from Course course, Enrollment enrollment " +
            "where course.id = enrollment.course.id " +
            "and enrollment.user.id = ?1")
    List<Course> findByUserId(int userId);
}
