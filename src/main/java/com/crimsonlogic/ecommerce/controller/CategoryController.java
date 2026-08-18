package com.crimsonlogic.ecommerce.controller;

import com.crimsonlogic.ecommerce.model.Category;
import com.crimsonlogic.ecommerce.service.CategoryService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    // ==========================================================
    // List Categories
    // ==========================================================

    @GetMapping("/list")
    public String listCategories(Model model) {

        List<Category> categories =
                categoryService.findAllCategories();

        model.addAttribute(
                "categories",
                categories
        );

        return "category/categories";
    }


    // ==========================================================
    // Show Add Category Page
    // ==========================================================

    @GetMapping("/add")
    public String showAddCategoryForm(Model model) {

        model.addAttribute(
                "category",
                new Category()
        );

        return "category/category-form";
    }


    // ==========================================================
    // Insert Category
    // ==========================================================

    @PostMapping("/add")
    public String addCategory(
            @ModelAttribute Category category) {

        categoryService.insertCategory(category);

        return "redirect:/category/list";
    }


    // ==========================================================
    // Show Edit Category Page
    // ==========================================================

    @GetMapping("/edit/{categoryId}")
    public String showEditCategoryForm(
            @PathVariable String categoryId,
            Model model) {

        Category category =
                categoryService.findCategoryById(
                        categoryId
                );

        if (category == null) {
            return "redirect:/category/list";
        }

        model.addAttribute(
                "category",
                category
        );

        return "category/category-form";
    }


    // ==========================================================
    // Update Category
    // ==========================================================

    @PostMapping("/edit")
    public String updateCategory(
            @ModelAttribute Category category) {

        categoryService.updateCategory(category);

        return "redirect:/category/list";
    }


    // ==========================================================
    // Delete Category
    // ==========================================================

    @PostMapping("/delete/{categoryId}")
    public String deleteCategory(
            @PathVariable String categoryId) {

        categoryService.deleteCategory(categoryId);

        return "redirect:/category/list";
    }
}