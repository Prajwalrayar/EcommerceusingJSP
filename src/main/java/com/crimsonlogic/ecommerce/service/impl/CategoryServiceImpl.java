package com.crimsonlogic.ecommerce.service.impl;

import com.crimsonlogic.ecommerce.dao.CategoryMapper;
import com.crimsonlogic.ecommerce.model.Category;
import com.crimsonlogic.ecommerce.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private CategoryMapper categoryMapper;


    public void setCategoryMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }


    // ==========================================================
    // Insert
    // ==========================================================

    @Override
    public void insertCategory(Category category) {

        categoryMapper.insertCategory(category);
    }


    // ==========================================================
    // Update
    // ==========================================================

    @Override
    public void updateCategory(Category category) {

        categoryMapper.updateCategory(category);
    }


    // ==========================================================
    // Delete
    // ==========================================================

    @Override
    public void deleteCategory(String categoryId) {

        categoryMapper.deleteCategory(categoryId);
    }


    // ==========================================================
    // Find By ID
    // ==========================================================

    @Override
    public Category findCategoryById(String categoryId) {

        return categoryMapper.findCategoryById(categoryId);
    }


    // ==========================================================
    // Find By Name
    // ==========================================================

    @Override
    public Category findCategoryByName(String categoryName) {

        return categoryMapper.findCategoryByName(categoryName);
    }


    // ==========================================================
    // Find All
    // ==========================================================

    @Override
    public List<Category> findAllCategories() {

        return categoryMapper.findAllCategories();
    }
}