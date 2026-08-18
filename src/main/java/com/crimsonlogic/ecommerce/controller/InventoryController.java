package com.crimsonlogic.ecommerce.controller;

import com.crimsonlogic.ecommerce.model.Admin;
import com.crimsonlogic.ecommerce.model.Inventory;
import com.crimsonlogic.ecommerce.model.Product;
import com.crimsonlogic.ecommerce.model.Seller;
import com.crimsonlogic.ecommerce.service.InventoryService;
import com.crimsonlogic.ecommerce.service.ProductService;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;
    private final ProductService productService;


    public InventoryController(
            InventoryService inventoryService,
            ProductService productService) {

        this.inventoryService =
                inventoryService;

        this.productService =
                productService;
    }


    // ==========================================================
    // LIST INVENTORY
    // ==========================================================

    @GetMapping("/list")
    public String listInventory(
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

            List<Inventory> inventoryList =
                    inventoryService.findInventoryBySeller(
                            seller.getUserId()
                    );

            model.addAttribute(
                    "inventoryList",
                    inventoryList
            );

            return "inventory/inventories";
        }


        // ------------------------------------------------------
        // ADMIN
        // ------------------------------------------------------

        if (loggedInUser instanceof Admin) {

            List<Inventory> inventoryList =
                    inventoryService.findAllInventory();

            model.addAttribute(
                    "inventoryList",
                    inventoryList
            );

            return "inventory/inventories";
        }


        return "redirect:/";
    }


    // ==========================================================
    // VIEW INVENTORY
    // ==========================================================

    @GetMapping("/view/{inventoryId}")
    public String viewInventory(
            @PathVariable String inventoryId,
            HttpSession session,
            Model model) {

        Object loggedInUser =
                session.getAttribute("loggedInUser");


        Inventory inventory =
                inventoryService.findInventoryById(
                        inventoryId
                );


        if (inventory == null) {

            return "redirect:/inventory/list";
        }


        // ------------------------------------------------------
        // SELLER
        // ------------------------------------------------------

        if (loggedInUser instanceof Seller) {

            Seller seller =
                    (Seller) loggedInUser;


            Inventory ownedInventory =
                    inventoryService
                            .findInventoryByIdAndSeller(
                                    inventoryId,
                                    seller.getUserId()
                            );


            if (ownedInventory == null) {

                return "redirect:/inventory/list";
            }

            inventory = ownedInventory;
        }


        model.addAttribute(
                "inventory",
                inventory
        );


        return "inventory/inventory-details";
    }


    // ==========================================================
    // ADD INVENTORY FORM
    // ==========================================================

    @GetMapping("/add")
    public String showAddInventoryForm(
            HttpSession session,
            Model model) {

        Object loggedInUser =
                session.getAttribute("loggedInUser");


        if (!(loggedInUser instanceof Seller)
                && !(loggedInUser instanceof Admin)) {

            return "redirect:/";
        }


        model.addAttribute(
                "inventory",
                new Inventory()
        );


        List<Product> products;


        // ------------------------------------------------------
        // SELLER → ONLY HIS PRODUCTS
        // ------------------------------------------------------

        if (loggedInUser instanceof Seller) {

            Seller seller =
                    (Seller) loggedInUser;

            products =
                    productService.findProductsBySeller(
                            seller.getUserId()
                    );
        }


        // ------------------------------------------------------
        // ADMIN → ONLY ADMIN PRODUCTS
        // ------------------------------------------------------

        else {

            products =
                    productService.findProductsBySeller(
                            null
                    );
        }


        model.addAttribute(
                "products",
                products
        );


        return "inventory/inventory-form";
    }


    // ==========================================================
    // INSERT INVENTORY
    // ==========================================================

    @PostMapping("/add")
    public String addInventory(
            @ModelAttribute Inventory inventory,
            HttpSession session) {

        Object loggedInUser =
                session.getAttribute("loggedInUser");


        if (loggedInUser instanceof Seller) {

            Seller seller =
                    (Seller) loggedInUser;


            Product product =
                    productService.findProductByIdAndSeller(
                            inventory.getProduct().getProductId(),
                            seller.getUserId()
                    );


            if (product == null) {

                return "redirect:/inventory/list";
            }


            inventory.setProduct(product);

            inventoryService.insertInventory(
                    inventory
            );

            return "redirect:/inventory/list";
        }


        if (loggedInUser instanceof Admin) {

            Product product =
                    productService.findProductById(
                            inventory.getProduct().getProductId()
                    );


            if (product == null) {

                return "redirect:/inventory/list";
            }


            /*
             * Admin can create inventory only
             * for admin-created products.
             */

            if (product.getSeller() != null) {

                return "redirect:/inventory/list";
            }


            inventory.setProduct(product);

            inventoryService.insertInventory(
                    inventory
            );

            return "redirect:/inventory/list";
        }


        return "redirect:/";
    }


    // ==========================================================
    // EDIT INVENTORY FORM
    // ==========================================================

    @GetMapping("/edit/{inventoryId}")
    public String showEditInventoryForm(
            @PathVariable String inventoryId,
            HttpSession session,
            Model model) {

        Object loggedInUser =
                session.getAttribute("loggedInUser");


        Inventory inventory =
                inventoryService.findInventoryById(
                        inventoryId
                );


        if (inventory == null) {

            return "redirect:/inventory/list";
        }


        // ------------------------------------------------------
        // SELLER
        // ------------------------------------------------------

        if (loggedInUser instanceof Seller) {

            Seller seller =
                    (Seller) loggedInUser;


            inventory =
                    inventoryService
                            .findInventoryByIdAndSeller(
                                    inventoryId,
                                    seller.getUserId()
                            );


            if (inventory == null) {

                return "redirect:/inventory/list";
            }


            List<Product> products =
                    productService.findProductsBySeller(
                            seller.getUserId()
                    );


            model.addAttribute(
                    "products",
                    products
            );
        }


        // ------------------------------------------------------
        // ADMIN
        // ------------------------------------------------------

        else if (loggedInUser instanceof Admin) {

            /*
             * Admin can edit only inventory
             * belonging to admin-created products.
             *
             * Admin product = seller == null
             */

            if (inventory.getProduct().getSeller() != null) {

                return "redirect:/inventory/list";
            }


            /*
             * Admin should see only admin-created
             * products in the product dropdown.
             */

            List<Product> products =
                    productService.findProductsBySeller(
                            null
                    );


            model.addAttribute(
                    "products",
                    products
            );
        }


        else {

            return "redirect:/";
        }


        model.addAttribute(
                "inventory",
                inventory
        );


        return "inventory/inventory-form";
    }


    // ==========================================================
    // UPDATE INVENTORY
    // ==========================================================

    @PostMapping("/edit")
    public String updateInventory(
            @ModelAttribute Inventory inventory,
            HttpSession session) {

        Object loggedInUser =
                session.getAttribute("loggedInUser");


        // ------------------------------------------------------
        // SELLER
        // ------------------------------------------------------

        if (loggedInUser instanceof Seller) {

            Seller seller =
                    (Seller) loggedInUser;


            Inventory existing =
                    inventoryService
                            .findInventoryByIdAndSeller(
                                    inventory.getInventoryId(),
                                    seller.getUserId()
                            );


            if (existing == null) {

                return "redirect:/inventory/list";
            }


            Product product =
                    productService.findProductByIdAndSeller(
                            inventory.getProduct().getProductId(),
                            seller.getUserId()
                    );


            if (product == null) {

                return "redirect:/inventory/list";
            }


            inventory.setProduct(product);


            inventoryService.updateInventory(
                    inventory
            );


            return "redirect:/inventory/list";
        }


        // ------------------------------------------------------
        // ADMIN
        // ------------------------------------------------------

        if (loggedInUser instanceof Admin) {

            Inventory existing =
                    inventoryService.findInventoryById(
                            inventory.getInventoryId()
                    );


            if (existing == null) {

                return "redirect:/inventory/list";
            }


            /*
             * Cannot edit seller inventory.
             */

            if (existing.getProduct().getSeller() != null) {

                return "redirect:/inventory/list";
            }


            Product product =
                    productService.findProductById(
                            inventory.getProduct().getProductId()
                    );


            if (product == null) {

                return "redirect:/inventory/list";
            }


            /*
             * Cannot change admin inventory
             * to a seller's product.
             */

            if (product.getSeller() != null) {

                return "redirect:/inventory/list";
            }


            inventory.setProduct(product);


            inventoryService.updateInventory(
                    inventory
            );


            return "redirect:/inventory/list";
        }


        return "redirect:/";
    }


    // ==========================================================
    // UPDATE QUANTITY
    // ==========================================================

    @PostMapping("/quantity/update")
    public String updateQuantity(
            @ModelAttribute Inventory inventory,
            HttpSession session) {

        Object loggedInUser =
                session.getAttribute("loggedInUser");


        if (loggedInUser instanceof Seller) {

            Seller seller =
                    (Seller) loggedInUser;


            Inventory existing =
                    inventoryService
                            .findInventoryByIdAndSeller(
                                    inventory.getInventoryId(),
                                    seller.getUserId()
                            );


            if (existing == null) {

                return "redirect:/inventory/list";
            }
        }


        if (loggedInUser instanceof Admin) {

            Inventory existing =
                    inventoryService.findInventoryById(
                            inventory.getInventoryId()
                    );


            if (existing == null) {

                return "redirect:/inventory/list";
            }


            if (existing.getProduct().getSeller() != null) {

                return "redirect:/inventory/list";
            }
        }


        inventoryService.updateQuantity(
                inventory
        );


        return "redirect:/inventory/list";
    }


    // ==========================================================
    // DELETE INVENTORY
    // ==========================================================

    @PostMapping("/delete/{inventoryId}")
    public String deleteInventory(
            @PathVariable String inventoryId,
            HttpSession session) {

        Object loggedInUser =
                session.getAttribute("loggedInUser");


        Inventory inventory =
                inventoryService.findInventoryById(
                        inventoryId
                );


        if (inventory == null) {

            return "redirect:/inventory/list";
        }


        // ------------------------------------------------------
        // SELLER
        // ------------------------------------------------------

        if (loggedInUser instanceof Seller) {

            Seller seller =
                    (Seller) loggedInUser;


            Inventory owned =
                    inventoryService
                            .findInventoryByIdAndSeller(
                                    inventoryId,
                                    seller.getUserId()
                            );


            if (owned == null) {

                return "redirect:/inventory/list";
            }


            inventoryService.deleteInventory(
                    inventoryId
            );


            return "redirect:/inventory/list";
        }


        // ------------------------------------------------------
        // ADMIN
        // ------------------------------------------------------

        if (loggedInUser instanceof Admin) {

            if (inventory.getProduct().getSeller() != null) {

                return "redirect:/inventory/list";
            }


            inventoryService.deleteInventory(
                    inventoryId
            );


            return "redirect:/inventory/list";
        }


        return "redirect:/";
    }


    // ==========================================================
    // INVENTORY BY SELLER
    // ==========================================================

    @GetMapping("/seller/{sellerId}")
    public String inventoryBySeller(
            @PathVariable String sellerId,
            HttpSession session,
            Model model) {

        Object loggedInUser =
                session.getAttribute("loggedInUser");


        if (loggedInUser instanceof Seller) {

            Seller seller =
                    (Seller) loggedInUser;


            /*
             * Seller cannot request another seller's
             * inventory by changing URL.
             */

            if (!seller.getUserId().equals(sellerId)) {

                return "redirect:/inventory/list";
            }
        }


        List<Inventory> inventoryList =
                inventoryService.findInventoryBySeller(
                        sellerId
                );


        model.addAttribute(
                "inventoryList",
                inventoryList
        );


        model.addAttribute(
                "sellerId",
                sellerId
        );


        return "inventory/inventories";
    }
}