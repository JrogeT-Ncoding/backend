package com.jroget.ncodingbackend.controllers;

import com.jroget.ncodingbackend.exceptions.NotFoundException;
import com.jroget.ncodingbackend.models.Category;
import com.jroget.ncodingbackend.models.Response;
import com.jroget.ncodingbackend.models.User;
import com.jroget.ncodingbackend.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Response> createC(@RequestBody Category category) {
        try {
            Category categoryCreated = categoryService.register(category);
            return this.sendResponseOk("Category created successfully", categoryCreated);
        }catch (Exception e) {
            return this.sendResponseServerError("Error creating categories: " + e.getMessage(),null);
        }
    }

    @GetMapping()
    public ResponseEntity<Response> getAll(){
        try{
            List<Category> users = categoryService.getCategories();
            return this.sendResponseOk("Categories retrieved successfully", users);
        }catch (Exception e){
            return this.sendResponseServerError("Error retrieving categories: " + e.getMessage(),null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getOne(@PathVariable("id") int id){
        try{
            Category updatedUser = categoryService.getCategory(id);
            return this.sendResponseOk("Category retrieved successfully", updatedUser);
        }catch (NotFoundException e) {
            return this.sendResponseNotFound("Category not found");
        }catch (Exception e){
            return this.sendResponseServerError("Error getting category: " + e.getMessage(),null);
        }
    }
}
