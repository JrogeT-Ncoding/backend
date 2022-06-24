package com.jroget.ncodingbackend.controllers;

import com.jroget.ncodingbackend.exceptions.NotFoundException;
import com.jroget.ncodingbackend.models.Category;
import com.jroget.ncodingbackend.models.Course;
import com.jroget.ncodingbackend.models.Response;
import com.jroget.ncodingbackend.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController extends BaseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<Response> create(@RequestBody Course course) {
        try {
            Course courseCreated = courseService.register(course);
            return this.sendResponseOk("Course created successfully", courseCreated);
        }catch (NotFoundException e) {
            return this.sendResponseNotFound(e.getMessage());
        }catch (Exception e) {
            return this.sendResponseServerError("Error creating course: " + e.getMessage(),course);
        }
    }

    @GetMapping
    public ResponseEntity<Response> getAll() {
        try {
            List<Course> courses = courseService.getAll();
            return this.sendResponseOk("Courses retrieved successfully", courses);
        }catch (Exception e) {
            return this.sendResponseServerError("Error retrieving courses: " + e.getMessage(), null);
        }
    }
    @GetMapping("/available")
    public ResponseEntity<Response> getAllAvailables(){
        try{
            List<Course> courses = courseService.getAvailableCourses();
            return this.sendResponseOk("Courses retrieved successfully", courses);
        }catch (Exception e){
            return this.sendResponseServerError("Error retrieving courses: " + e.getMessage(),null);
        }
    }

    @GetMapping("/started")
    public ResponseEntity<Response> getAllStarted(){
        try{
            List<Course> courses = courseService.getStartedCourses();
            return this.sendResponseOk("Courses retrieved successfully", courses);
        }catch (Exception e){
            return this.sendResponseServerError("Error retrieving courses: " + e.getMessage(),null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getOne(@PathVariable("id") int id){
        try{
            Course updatedCourse = courseService.getCourse(id);
            return this.sendResponseOk("Course retrieved successfully", updatedCourse);
        }catch (NotFoundException e) {
            return this.sendResponseNotFound("Course not found");
        }catch (Exception e){
            return this.sendResponseServerError("Error getting course: " + e.getMessage(),null);
        }
    }
}
