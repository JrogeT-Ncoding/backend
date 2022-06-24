package com.jroget.ncodingbackend.services;

import com.jroget.ncodingbackend.exceptions.NotFoundException;
import com.jroget.ncodingbackend.models.Category;
import com.jroget.ncodingbackend.models.Course;
import com.jroget.ncodingbackend.repositories.CategoryRepository;
import com.jroget.ncodingbackend.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Course register(Course course) throws Exception {
        try{
            return courseRepository.save(course);
        }catch (Exception e){
            throw new Exception("Error registering course: " + e.getMessage());
        }
    }

    public List<Course> getCourses(){
        return (List<Course>) courseRepository.findAll();
    }

    public Course getCourse(int id) throws NotFoundException {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isEmpty()) {
            throw new NotFoundException("Course not found");
        }
        return optionalCourse.get();
    }

    public List<Course> getCoursesFromCategory(int categoryId) throws NotFoundException {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isEmpty()) {
            throw new NotFoundException("Category not found");
        }
        return courseRepository.findByCategory(optionalCategory.get());
    }
}
