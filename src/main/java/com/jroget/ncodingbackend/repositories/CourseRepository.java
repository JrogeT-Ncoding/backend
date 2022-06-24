package com.jroget.ncodingbackend.repositories;

import com.jroget.ncodingbackend.models.Category;
import com.jroget.ncodingbackend.models.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, Integer> {

    List<Course> findByCategory(Category category);
}
