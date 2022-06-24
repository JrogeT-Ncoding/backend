package com.jroget.ncodingbackend.services;

import com.jroget.ncodingbackend.exceptions.NotFoundException;
import com.jroget.ncodingbackend.models.Category;
import com.jroget.ncodingbackend.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category register(Category category) throws Exception {
        try{
            return categoryRepository.save(category);
        }catch (Exception e){
            throw new Exception("Error registering category: " + e.getMessage());
        }
    }

    public List<Category> getCategories(){
        return (List<Category>) categoryRepository.findAll();
    }

    public Category getCategory(int id) throws NotFoundException {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            throw new NotFoundException("Category not found");
        }
        return optionalCategory.get();
    }
}
