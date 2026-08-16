package com.crimsonlogic.ecommerce.controller;

import com.crimsonlogic.ecommerce.model.Inventory;
import com.crimsonlogic.ecommerce.model.Product;
import com.crimsonlogic.ecommerce.service.InventoryService;
import com.crimsonlogic.ecommerce.service.ProductService;

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

        this.inventoryService = inventoryService;
        this.productService = productService;
    }


    // ==========================================================
    // List All Inventory
    // ==========================================================

    @GetMapping("/list")
    public String listInventory(
            Model model) {

        List<Inventory> inventoryList =
                inventoryService.findAllInventory();

        model.addAttribute(
                "inventoryList",
                inventoryList
        );

        return "inventory";
    }


    // ==========================================================
    // View Inventory
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

        return "inventory-details";
    }


    // ==========================================================
    // Add Inventory Form
    // ==========================================================

    @GetMapping("/add")
    public String showAddInventoryForm(
            Model model) {

        model.addAttribute(
                "inventory",
                new Inventory()
        );

        List<Product> products =
                productService.findAllProducts();

        model.addAttribute(
                "products",
                products
        );

        return "inventory-form";
    }


    // ==========================================================
    // Insert Inventory
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
    // Edit Inventory Form
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

        List<Product> products =
                productService.findAllProducts();

        model.addAttribute(
                "products",
                products
        );

        return "inventory-form";
    }


    // ==========================================================
    // Update Inventory
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
    // Update Quantity
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
    // Delete Inventory
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
    // Inventory By Seller
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

        return "inventory";
    }
}