package com.crimsonlogic.ecommerce.model;

/**
 * Represents a category in the ecommerce application.
 *
 * A Category contains the identifying information and descriptive
 * details used to organize products within the application.
 */
public class Category {

    /**
     * Unique identifier of the category.
     */
    private String categoryId;

    /**
     * Name of the category.
     */
    private String categoryName;

    /**
     * Description providing additional information about the category.
     */
    private String categoryDescription;

    /**
     * Default constructor.
     *
     * Creates an empty Category object that can be populated
     * using the setter methods.
     */
    public Category() {

    }

    /**
     * Creates a Category object using the supplied category information.
     *
     * @param categoryId Category ID
     * @param categoryName Category Name
     * @param categoryDescription Category Description
     */
    public Category(String categoryId,

                    String categoryName,

                    String categoryDescription) {

        this.categoryId = categoryId;

        this.categoryName = categoryName;

        this.categoryDescription = categoryDescription;

    }

    // =====================================================
    // Getters & Setters
    // =====================================================

    /**
     * Returns the unique identifier of the category.
     *
     * @return category ID
     */
    public String getCategoryId() {

        return categoryId;

    }

    /**
     * Updates the unique identifier of the category.
     *
     * @param categoryId new category ID
     */
    public void setCategoryId(String categoryId) {

        this.categoryId = categoryId;

    }

    /**
     * Returns the name of the category.
     *
     * @return category name
     */
    public String getCategoryName() {

        return categoryName;

    }

    /**
     * Updates the name of the category.
     *
     * @param categoryName new category name
     */
    public void setCategoryName(String categoryName) {

        this.categoryName = categoryName;

    }

    /**
     * Returns the description of the category.
     *
     * @return category description
     */
    public String getCategoryDescription() {

        return categoryDescription;

    }

    /**
     * Updates the description of the category.
     *
     * @param categoryDescription new category description
     */
    public void setCategoryDescription(String categoryDescription) {

        this.categoryDescription = categoryDescription;

    }

}