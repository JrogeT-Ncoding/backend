package com.jroget.ncodingbackend.services;

import com.jroget.ncodingbackend.exceptions.NotAllowedException;
import com.jroget.ncodingbackend.exceptions.NotFoundException;
import com.jroget.ncodingbackend.models.Course;
import com.jroget.ncodingbackend.models.Enrollment;
import com.jroget.ncodingbackend.models.User;
import com.jroget.ncodingbackend.repositories.CourseRepository;
import com.jroget.ncodingbackend.repositories.EnrollmentRepository;
import com.jroget.ncodingbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    public Enrollment register(Enrollment enrollment) throws Exception {
        try{
            Optional<User> user = userRepository.findById(enrollment.getUser().getId());
            if(!user.isPresent()){
                throw new NotFoundException("User not found");
            }
            Optional<Course> course = courseRepository.findById(enrollment.getCourse().getId());
            if(!course.isPresent()){
                throw new NotFoundException("Course not found");
            }
            if(course.get().getAvailableCapacity() <=0 ){
                throw new NotAllowedException("No more seats available");
            }
            Optional<Enrollment> enrollmentOptional = enrollmentRepository.findByUser_IdAndCourse_Id(
                    user.get().getId(),
                    course.get().getId()
            );
            if(enrollmentOptional.isPresent()){
                throw new NotAllowedException("User already enrolled in this course");
            }
            List<Enrollment> enrollments = enrollmentRepository.findByCourse_Id(course.get().getId());
            if(enrollments.size() >= course.get().getMaxCapacity()){
                throw new NotAllowedException("Course is full");
            }
            enrollment.setEnrollmentDate(new Date());
            Enrollment savedEnrollment = enrollmentRepository.save(enrollment);

            course.get().setAvailableCapacity(course.get().getAvailableCapacity() - 1);
            courseRepository.save(course.get());

            return savedEnrollment;
        }catch (Exception e){
            throw new Exception("Error registering enrollment: " + e.getMessage());
        }
    }

    public Enrollment getEnrollment(int id) throws NotFoundException {
        Optional<Enrollment> optionalEnrollment = enrollmentRepository.findById(id);
        if (optionalEnrollment.isEmpty()) {
            throw new NotFoundException("Enrollment not found");
        }
        return optionalEnrollment.get();
    }

    public List<Enrollment> getEnrollmentsFromUser(int userId) {
        return enrollmentRepository.findByUser_Id(userId);
    }

    public void delete(int userId, int courseId) throws NotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()){
            throw new NotFoundException("User not found");
        }
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if (optionalCourse.isEmpty()) {
            throw new NotFoundException("Course not found");
        }
        Optional<Enrollment> optionalEnrollment = enrollmentRepository.findByUser_IdAndCourse_Id(userId, courseId);
        if (optionalEnrollment.isEmpty()) {
            throw new NotFoundException("Enrollment not found");
        }
        Course course = optionalCourse.get();
        course.setAvailableCapacity(course.getAvailableCapacity() + 1);
        courseRepository.save(course);

        enrollmentRepository.delete(optionalEnrollment.get());
    }
}
