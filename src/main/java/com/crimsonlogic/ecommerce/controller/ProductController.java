package com.crimsonlogic.ecommerce.controller;

import com.crimsonlogic.ecommerce.enumeration.ProductStatus;
import com.crimsonlogic.ecommerce.model.Category;
import com.crimsonlogic.ecommerce.model.Product;
import com.crimsonlogic.ecommerce.model.Seller;
import com.crimsonlogic.ecommerce.model.Admin;
import com.crimsonlogic.ecommerce.service.CategoryService;
import com.crimsonlogic.ecommerce.service.ProductService;
import com.crimsonlogic.ecommerce.service.SellerService;
import com.crimsonlogic.ecommerce.util.IdGenerator;

import javax.servlet.http.HttpSession;

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
    // LIST PRODUCTS
    // ==========================================================

    @GetMapping("/list")
    public String listProducts(
            HttpSession session,
            Model model) {

        Object loggedInUser =
                session.getAttribute("loggedInUser");

        if (loggedInUser instanceof Seller) {

            Seller seller =
                    (Seller) loggedInUser;

            List<Product> products =
                    productService.findProductsBySeller(
                            seller.getUserId());

            model.addAttribute(
                    "products",
                    products);

            model.addAttribute(
                    "seller",
                    seller);

            return "product/products";
        }


        if (loggedInUser instanceof Admin) {

            List<Product> products =
                    productService.findAllProducts();

            model.addAttribute(
                    "products",
                    products);

            return "admin/products";
        }


        return "redirect:/";
    }


    // ==========================================================
    // PRODUCT DETAILS
    // ==========================================================

    @GetMapping("/view/{productId}")
    public String viewProduct(
            @PathVariable String productId,
            HttpSession session,
            Model model) {

        Product product =
                productService.findProductById(productId);

        if (product == null) {

            return "redirect:/product/list";
        }


        model.addAttribute(
                "product",
                product);

        return "product/product-details";
    }


    // ==========================================================
    // ADD PRODUCT FORM
    // ==========================================================

    @GetMapping("/add")
    public String showAddProductForm(
            HttpSession session,
            Model model) {

        Object loggedInUser =
                session.getAttribute("loggedInUser");

        if (!(loggedInUser instanceof Seller)
                && !(loggedInUser instanceof Admin)) {

            return "redirect:/";
        }

        Product product = new Product();

        model.addAttribute(
                "product",
                product
        );

        loadFormData(model);

        return "product/product-form";
    }


    // ==========================================================
    // INSERT PRODUCT

    @PostMapping("/add")
    public String addProduct(
            @ModelAttribute Product product,
            HttpSession session) {

        Object loggedInUser =
                session.getAttribute("loggedInUser");


        // ------------------------------------------------------
        // SELLER
        // ------------------------------------------------------

        if (loggedInUser instanceof Seller) {

            Seller seller =
                    (Seller) loggedInUser;

            product.setSeller(seller);

            product.setCreatedBy(
                    seller.getUserId()
            );

            productService.insertProduct(product);

            return "redirect:/product/list";
        }


        // ------------------------------------------------------
        // ADMIN
        // ------------------------------------------------------

        if (loggedInUser instanceof Admin) {

            Admin admin =
                    (Admin) loggedInUser;

            /*
             * Admin-created product does not belong
             * to a seller.
             */

            product.setSeller(null);

            product.setCreatedBy(
                    admin.getUserId()
            );

            productService.insertProduct(product);

            return "redirect:/product/list";
        }


        return "redirect:/";
    }


    // ==========================================================
    // EDIT PRODUCT FORM
    // ==========================================================

    @GetMapping("/edit/{productId}")
    public String showEditProductForm(
            @PathVariable String productId,
            HttpSession session,
            Model model) {

        Object loggedInUser =
                session.getAttribute("loggedInUser");


        // ------------------------------------------------------
        // SELLER
        // ------------------------------------------------------

        if (loggedInUser instanceof Seller) {

            Seller seller =
                    (Seller) loggedInUser;


            /*
             * IMPORTANT:
             *
             * Search the database using BOTH:
             *
             * productId
             * sellerId
             *
             * This prevents Seller A from editing
             * Seller B's product.
             */

            Product product =
                    productService.findProductByIdAndSeller(
                            productId,
                            seller.getUserId());


            if (product == null) {

                return "redirect:/product/list";
            }


            model.addAttribute(
                    "product",
                    product);

            loadFormData(model);

            return "product/product-form";
        }


        // ------------------------------------------------------
        // ADMIN
        // ------------------------------------------------------

        if (loggedInUser instanceof Admin) {

            Product product =
                    productService.findProductById(
                            productId);


            if (product == null) {

                return "redirect:/product/list";
            }


            /*
             * ADMIN CAN EDIT ONLY ADMIN-CREATED PRODUCTS.
             *
             * Admin-created products have seller == null.
             */

            if (product.getSeller() != null) {

                return "redirect:/product/list";
            }


            model.addAttribute(
                    "product",
                    product);

            loadFormData(model);

            return "product/product-form";
        }


        return "redirect:/";
    }


    // ==========================================================
    // UPDATE PRODUCT
    // ==========================================================

    @PostMapping("/edit")
    public String updateProduct(
            @ModelAttribute Product product,
            HttpSession session) {

        Object loggedInUser =
                session.getAttribute("loggedInUser");


        // ------------------------------------------------------
        // SELLER
        // ------------------------------------------------------

        if (loggedInUser instanceof Seller) {

            Seller seller =
                    (Seller) loggedInUser;


            Product existingProduct =
                    productService.findProductByIdAndSeller(
                            product.getProductId(),
                            seller.getUserId());


            /*
             * Product does not belong to seller.
             */

            if (existingProduct == null) {

                return "redirect:/product/list";
            }


            /*
             * Never trust sellerId coming from the JSP.
             *
             * Force the authenticated seller.
             */

            product.setSeller(seller);

            productService.updateProduct(product);

            return "redirect:/product/list";
        }


        // ------------------------------------------------------
        // ADMIN
        // ------------------------------------------------------

        if (loggedInUser instanceof Admin) {

            Product existingProduct =
                    productService.findProductById(
                            product.getProductId());


            if (existingProduct == null) {

                return "redirect:/product/list";
            }


            /*
             * Admin can modify ONLY products created by admin.
             *
             * Seller-owned product = seller != null
             */

            if (existingProduct.getSeller() != null) {

                return "redirect:/product/list";
            }


            product.setSeller(null);

            productService.updateProduct(product);

            return "redirect:/product/list";
        }


        return "redirect:/";
    }


    // ==========================================================
    // DELETE PRODUCT
    // ==========================================================

    @PostMapping("/delete/{productId}")
    public String deleteProduct(
            @PathVariable String productId,
            HttpSession session) {

        Object loggedInUser =
                session.getAttribute("loggedInUser");


        // ------------------------------------------------------
        // SELLER
        // ------------------------------------------------------

        if (loggedInUser instanceof Seller) {

            Seller seller =
                    (Seller) loggedInUser;


            Product product =
                    productService.findProductByIdAndSeller(
                            productId,
                            seller.getUserId());


            if (product == null) {

                return "redirect:/product/list";
            }


            productService.deleteProduct(productId);

            return "redirect:/product/list";
        }


        // ------------------------------------------------------
        // ADMIN
        // ------------------------------------------------------

        if (loggedInUser instanceof Admin) {

            Product product =
                    productService.findProductById(
                            productId);


            if (product == null) {

                return "redirect:/product/list";
            }


            /*
             * Admin can delete only admin-created products.
             */

            if (product.getSeller() != null) {

                return "redirect:/product/list";
            }


            productService.deleteProduct(productId);

            return "redirect:/product/list";
        }


        return "redirect:/";
    }


    // ==========================================================
    // PRODUCTS BY SELLER
    // ==========================================================

    @GetMapping("/seller/{sellerId}")
    public String productsBySeller(
            @PathVariable String sellerId,
            HttpSession session,
            Model model) {

        Object loggedInUser =
                session.getAttribute("loggedInUser");


        /*
         * A seller can see ONLY his own products.
         */

        if (loggedInUser instanceof Seller) {

            Seller seller =
                    (Seller) loggedInUser;


            if (!seller.getUserId().equals(sellerId)) {

                return "redirect:/product/list";
            }
        }


        List<Product> products =
                productService.findProductsBySeller(
                        sellerId);


        model.addAttribute(
                "products",
                products);

        model.addAttribute(
                "sellerId",
                sellerId);

        return "product/products";
    }


    // ==========================================================
    // PRODUCTS BY CATEGORY
    // ==========================================================

    @GetMapping("/category/{categoryId}")
    public String productsByCategory(
            @PathVariable String categoryId,
            Model model) {

        List<Product> products =
                productService.findProductsByCategory(
                        categoryId);


        model.addAttribute(
                "products",
                products);

        model.addAttribute(
                "categoryId",
                categoryId);

        return "product/products";
    }


    // ==========================================================
    // AVAILABLE PRODUCTS
    // ==========================================================

    @GetMapping("/available")
    public String availableProducts(
            Model model) {

        List<Product> products =
                productService.findAvailableProducts();


        model.addAttribute(
                "products",
                products);

        return "product/products";
    }


    // ==========================================================
    // LOAD FORM DATA
    // ==========================================================

    private void loadFormData(
            Model model) {

        List<Category> categories =
                categoryService.findAllCategories();


        List<Seller> sellers =
                sellerService.findAllSellers();


        model.addAttribute(
                "categories",
                categories);


        model.addAttribute(
                "sellers",
                sellers);


        model.addAttribute(
                "statuses",
                ProductStatus.values());
    }
}