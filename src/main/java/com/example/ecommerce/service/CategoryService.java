package com.example.ecommerce.service;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    // Injecting the Category Repository
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Retrieve all categories
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Find a specific category by ID
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    // Save a new category or update an existing one
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Update an existing category
    public Category updateCategory(Long id, Category categoryDetails) {
        return categoryRepository.findById(id).map(existingCategory -> {
            existingCategory.setName(categoryDetails.getName());
            existingCategory.setDescription(categoryDetails.getDescription());
            return categoryRepository.save(existingCategory);
        }).orElseThrow(() -> new RuntimeException("Category not found with id " + id));
    }

    // Delete a category
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found with id " + id);
        }
        categoryRepository.deleteById(id);
    }
}