package com.crimsonlogic.ecommerce.dao;

import com.crimsonlogic.ecommerce.model.Category;

import java.util.List;

public interface CategoryMapper {

    /**
     * Inserts Category.
     */
    void insertCategory(Category category);


    /**
     * Updates Category.
     */
    void updateCategory(Category category);


    /**
     * Deletes Category.
     */
    void deleteCategory(String categoryId);


    /**
     * Finds Category by ID.
     */
    Category findCategoryById(String categoryId);


    /**
     * Finds Category by Name.
     */
    Category findCategoryByName(String categoryName);


    /**
     * Returns all Categories.
     */
    List<Category> findAllCategories();
}