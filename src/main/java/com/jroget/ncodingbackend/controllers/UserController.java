package com.jroget.ncodingbackend.controllers;

import com.jroget.ncodingbackend.exceptions.NotAllowedException;
import com.jroget.ncodingbackend.exceptions.NotFoundException;
import com.jroget.ncodingbackend.models.Enrollment;
import com.jroget.ncodingbackend.models.Response;
import com.jroget.ncodingbackend.models.User;
import com.jroget.ncodingbackend.services.EnrollmentService;
import com.jroget.ncodingbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping()
    public ResponseEntity<Response> getAll(){
        try{
            List<User> users = userService.getUsers();
            return this.sendResponseOk("Users retrieved successfully", users);
        }catch (Exception e){
            return this.sendResponseServerError("Error retrieving users: " + e.getMessage(),null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@PathVariable("id") int id, @RequestBody User user){
        try{
            User updatedUser = userService.update(id, user);
            return this.sendResponseOk("User updated successfully", updatedUser);
        }catch (NotFoundException e) {
            return this.sendResponseNotFound("User not found");
        }catch (Exception e){
            return this.sendResponseServerError("Error updating user: " + e.getMessage(),null);
        }
    }

    @GetMapping("/{id}/enrollments")
    public ResponseEntity<Response> getEnrollments(@PathVariable("id") int id){
        try{
            List<Enrollment> enrollments = enrollmentService.getEnrollmentsFromUser(id);
            return this.sendResponseOk("Enrollments retrieved successfully", enrollments);
        }catch (Exception e){
            return this.sendResponseServerError("Error retrieving enrollments: " + e.getMessage(),null);
        }
    }

    @PostMapping("/{id}/enrollments")
    public ResponseEntity<Response> addEnrollment(@PathVariable("id") int id, @RequestBody Enrollment enrollment){
        try{
            if (id != enrollment.getUser().getId()) {
                throw new NotAllowedException("User id does not match enrollment user id");
            }
            Enrollment newEnrollment = enrollmentService.register(enrollment);
            return this.sendResponseOk("Enrollment added successfully", newEnrollment);
        }catch (NotFoundException e) {
            return this.sendResponseNotFound(e.getMessage());
        }catch (NotAllowedException e) {
            return this.sendResponseNotAllowed(e.getMessage());
        }catch (Exception e){
            return this.sendResponseServerError(e.getMessage(),null);
        }
    }

    @DeleteMapping("/{id}/enrollments/{enrollmentId}")
    public ResponseEntity<Response> deleteEnrollment(@PathVariable("id") int id, @PathVariable("enrollmentId") int enrollmentId){
        try{
            enrollmentService.delete(id, enrollmentId);
            return this.sendResponseOk("Enrollment deleted successfully", null);
        }catch (NotFoundException e) {
            return this.sendResponseNotFound(e.getMessage());
        }catch (Exception e){
            return this.sendResponseServerError(e.getMessage(),null);
        }
    }

}
