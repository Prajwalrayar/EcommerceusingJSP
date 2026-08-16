package com.crimsonlogic.ecommerce.controller;

import com.crimsonlogic.ecommerce.enumeration.ProductStatus;
import com.crimsonlogic.ecommerce.model.Category;
import com.crimsonlogic.ecommerce.model.Product;
import com.crimsonlogic.ecommerce.model.Seller;
import com.crimsonlogic.ecommerce.service.CategoryService;
import com.crimsonlogic.ecommerce.service.ProductService;
import com.crimsonlogic.ecommerce.service.SellerService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final SellerService sellerService;


    public ProductController(
            ProductService productService,
            CategoryService categoryService,
            SellerService sellerService) {

        this.productService = productService;
        this.categoryService = categoryService;
        this.sellerService = sellerService;
    }


    // ==========================================================
    // List Products
    // ==========================================================

    @GetMapping("/list")
    public String listProducts(Model model) {

        List<Product> products =
                productService.findAllProducts();

        model.addAttribute(
                "products",
                products
        );

        return "products";
    }


    // ==========================================================
    // Product Details
    // ==========================================================

    @GetMapping("/view/{productId}")
    public String viewProduct(
            @PathVariable String productId,
            Model model) {

        Product product =
                productService.findProductById(productId);

        if (product == null) {
            return "redirect:/product/list";
        }

        model.addAttribute(
                "product",
                product
        );

        return "product-details";
    }


    // ==========================================================
    // Add Product Form
    // ==========================================================

    @GetMapping("/add")
    public String showAddProductForm(
            Model model) {

        model.addAttribute(
                "product",
                new Product()
        );

        loadFormData(model);

        return "product-form";
    }


    // ==========================================================
    // Insert Product
    // ==========================================================

    @PostMapping("/add")
    public String addProduct(
            @ModelAttribute Product product) {

        productService.insertProduct(product);

        return "redirect:/product/list";
    }


    // ==========================================================
    // Edit Product Form
    // ==========================================================

    @GetMapping("/edit/{productId}")
    public String showEditProductForm(
            @PathVariable String productId,
            Model model) {

        Product product =
                productService.findProductById(productId);

        if (product == null) {
            return "redirect:/product/list";
        }

        model.addAttribute(
                "product",
                product
        );

        loadFormData(model);

        return "product-form";
    }


    // ==========================================================
    // Update Product
    // ==========================================================

    @PostMapping("/edit")
    public String updateProduct(
            @ModelAttribute Product product) {

        productService.updateProduct(product);

        return "redirect:/product/list";
    }


    // ==========================================================
    // Delete Product
    // ==========================================================

    @PostMapping("/delete/{productId}")
    public String deleteProduct(
            @PathVariable String productId) {

        productService.deleteProduct(productId);

        return "redirect:/product/list";
    }


    // ==========================================================
    // Products By Seller
    // ==========================================================

    @GetMapping("/seller/{sellerId}")
    public String productsBySeller(
            @PathVariable String sellerId,
            Model model) {

        List<Product> products =
                productService.findProductsBySeller(
                        sellerId
                );

        model.addAttribute(
                "products",
                products
        );

        model.addAttribute(
                "sellerId",
                sellerId
        );

        return "products";
    }


    // ==========================================================
    // Products By Category
    // ==========================================================

    @GetMapping("/category/{categoryId}")
    public String productsByCategory(
            @PathVariable String categoryId,
            Model model) {

        List<Product> products =
                productService.findProductsByCategory(
                        categoryId
                );

        model.addAttribute(
                "products",
                products
        );

        model.addAttribute(
                "categoryId",
                categoryId
        );

        return "products";
    }


    // ==========================================================
    // Available Products
    // ==========================================================

    @GetMapping("/available")
    public String availableProducts(
            Model model) {

        List<Product> products =
                productService.findAvailableProducts();

        model.addAttribute(
                "products",
                products
        );

        return "products";
    }


    // ==========================================================
    // Load Categories + Sellers + Status
    // ==========================================================

    private void loadFormData(Model model) {

        List<Category> categories =
                categoryService.findAllCategories();

        List<Seller> sellers =
                sellerService.findAllSellers();

        model.addAttribute(
                "categories",
                categories
        );

        model.addAttribute(
                "sellers",
                sellers
        );

        model.addAttribute(
                "statuses",
                ProductStatus.values()
        );
    }
}