package com.crimsonlogic.ecommerce.model;

public class Category {

	/* Category ID */
    private String categoryId;

    /* Category Name */
    private String categoryName;

    /* Category Description */
    private String categoryDescription;

    /**
     * Default Constructor.
     */
    public Category() {
    }

    /**
     * Parameterized Constructor.
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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

}
