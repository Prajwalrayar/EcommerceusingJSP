package com.crimsonlogic.ecommerce.service.impl;

import com.crimsonlogic.ecommerce.dao.CategoryMapper;
import com.crimsonlogic.ecommerce.model.Category;
import com.crimsonlogic.ecommerce.service.CategoryService;
import com.crimsonlogic.ecommerce.util.IdGenerator;
import com.crimsonlogic.ecommerce.util.ValidationUtil;

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

        // ------------------------------------------------------
        // Generate Category ID automatically
        // ------------------------------------------------------

        if (category.getCategoryId() == null
                || category.getCategoryId().trim().isEmpty()) {

            category.setCategoryId(
                    IdGenerator.generateId("CAT")
            );
        }


        // ------------------------------------------------------
        // Validate Category
        // ------------------------------------------------------

        ValidationUtil.validateCategoryName(
                category.getCategoryName()
        );

        ValidationUtil.validateCategoryDescription(
                category.getCategoryDescription()
        );


        // ------------------------------------------------------
        // Insert
        // ------------------------------------------------------

        categoryMapper.insertCategory(category);
    }



    // ==========================================================
    // Update
    // ==========================================================

    @Override
    public void updateCategory(Category category) {

        ValidationUtil.validateCategoryName(
                category.getCategoryName()
        );

        ValidationUtil.validateCategoryDescription(
                category.getCategoryDescription()
        );

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