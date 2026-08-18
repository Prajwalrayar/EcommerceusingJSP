package com.crimsonlogic.ecommerce.controller;

import com.crimsonlogic.ecommerce.model.Inventory;
import com.crimsonlogic.ecommerce.model.Product;
import com.crimsonlogic.ecommerce.service.InventoryService;
import com.crimsonlogic.ecommerce.service.ProductService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;
    private final ProductService productService;


    // ==========================================================
    // CONSTRUCTOR
    // ==========================================================

    public InventoryController(
            InventoryService inventoryService,
            ProductService productService) {

        this.inventoryService = inventoryService;
        this.productService = productService;
    }


    // ==========================================================
    // LIST ALL INVENTORY
    // URL: /inventory/list
    // JSP: /WEB-INF/views/inventory/inventories.jsp
    // ==========================================================

    @GetMapping("/list")
    public String listInventory(Model model) {

        List<Inventory> inventoryList =
                inventoryService.findAllInventory();

        model.addAttribute(
                "inventoryList",
                inventoryList
        );

        return "inventory/inventories";
    }


    // ==========================================================
    // VIEW INVENTORY
    // URL: /inventory/view/{inventoryId}
    // JSP: /WEB-INF/views/inventory/inventory-details.jsp
    // ==========================================================

    @GetMapping("/view/{inventoryId}")
    public String viewInventory(
            @PathVariable String inventoryId,
            Model model) {

        Inventory inventory =
                inventoryService.findInventoryById(
                        inventoryId
                );

        if (inventory == null) {

            return "redirect:/inventory/list";
        }

        model.addAttribute(
                "inventory",
                inventory
        );

        return "inventory/inventory-details";
    }


    // ==========================================================
    // SHOW ADD INVENTORY FORM
    // URL: /inventory/add
    // JSP: /WEB-INF/views/inventory/inventory-form.jsp
    // ==========================================================

    @GetMapping("/add")
    public String showAddInventoryForm(
            Model model) {

        Inventory inventory =
                new Inventory();

        model.addAttribute(
                "inventory",
                inventory
        );


        // Load products for dropdown

        List<Product> products =
                productService.findAllProducts();

        model.addAttribute(
                "products",
                products
        );


        return "inventory/inventory-form";
    }


    // ==========================================================
    // INSERT INVENTORY
    // URL: /inventory/add
    // ==========================================================

    @PostMapping("/add")
    public String addInventory(
            @ModelAttribute Inventory inventory) {

        inventoryService.insertInventory(
                inventory
        );

        return "redirect:/inventory/list";
    }


    // ==========================================================
    // SHOW EDIT INVENTORY FORM
    // URL: /inventory/edit/{inventoryId}
    // JSP: /WEB-INF/views/inventory/inventory-form.jsp
    // ==========================================================

    @GetMapping("/edit/{inventoryId}")
    public String showEditInventoryForm(
            @PathVariable String inventoryId,
            Model model) {

        Inventory inventory =
                inventoryService.findInventoryById(
                        inventoryId
                );

        if (inventory == null) {

            return "redirect:/inventory/list";
        }


        model.addAttribute(
                "inventory",
                inventory
        );


        // Load products for dropdown

        List<Product> products =
                productService.findAllProducts();

        model.addAttribute(
                "products",
                products
        );


        return "inventory/inventory-form";
    }


    // ==========================================================
    // UPDATE INVENTORY
    // URL: /inventory/edit
    // ==========================================================

    @PostMapping("/edit")
    public String updateInventory(
            @ModelAttribute Inventory inventory) {

        inventoryService.updateInventory(
                inventory
        );

        return "redirect:/inventory/list";
    }


    // ==========================================================
    // UPDATE INVENTORY QUANTITY
    // URL: /inventory/quantity/update
    // ==========================================================

    @PostMapping("/quantity/update")
    public String updateQuantity(
            @ModelAttribute Inventory inventory) {

        inventoryService.updateQuantity(
                inventory
        );

        return "redirect:/inventory/list";
    }


    // ==========================================================
    // DELETE INVENTORY
    // URL: /inventory/delete/{inventoryId}
    // ==========================================================

    @PostMapping("/delete/{inventoryId}")
    public String deleteInventory(
            @PathVariable String inventoryId) {

        inventoryService.deleteInventory(
                inventoryId
        );

        return "redirect:/inventory/list";
    }


    // ==========================================================
    // INVENTORY BY SELLER
    // URL: /inventory/seller/{sellerId}
    // JSP: /WEB-INF/views/inventory/inventories.jsp
    // ==========================================================

    @GetMapping("/seller/{sellerId}")
    public String inventoryBySeller(
            @PathVariable String sellerId,
            Model model) {

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