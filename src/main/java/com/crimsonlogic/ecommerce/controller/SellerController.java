package com.crimsonlogic.ecommerce.controller;

import com.crimsonlogic.ecommerce.model.Address;
import com.crimsonlogic.ecommerce.model.Seller;
import com.crimsonlogic.ecommerce.service.AddressService;
import com.crimsonlogic.ecommerce.service.SellerService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/seller")
public class SellerController {

    private final SellerService sellerService;
    private final AddressService addressService;


    public SellerController(
            SellerService sellerService,
            AddressService addressService) {

        this.sellerService = sellerService;
        this.addressService = addressService;
    }


    // ==========================================================
    // SELLER PROFILE
    // ==========================================================

    @GetMapping("/profile/{sellerId}")
    public String viewProfile(
            @PathVariable String sellerId,
            Model model) {

        Seller seller =
                sellerService.findSellerById(sellerId);

        if (seller == null) {

            return "redirect:/admin/sellers";
        }


        List<Address> addresses =
                sellerService.findAddressesBySeller(
                        sellerId);


        model.addAttribute(
                "seller",
                seller);

        model.addAttribute(
                "addresses",
                addresses);


        return "seller/seller-profile";
    }


    // ==========================================================
    // EDIT SELLER PROFILE
    // ==========================================================

    @GetMapping("/profile/edit/{sellerId}")
    public String editProfile(
            @PathVariable String sellerId,
            Model model) {

        Seller seller =
                sellerService.findSellerById(
                        sellerId);

        if (seller == null) {

            return "redirect:/admin/sellers";
        }


        model.addAttribute(
                "seller",
                seller);


        return "seller/edit-profile";
    }


    // ==========================================================
    // UPDATE SELLER PROFILE
    // ==========================================================

    @PostMapping("/profile/update")
    public String updateProfile(
            @ModelAttribute Seller seller) {

        sellerService.updateSeller(
                seller);


        return "redirect:/seller/profile/"
                + seller.getUserId();
    }


    // ==========================================================
    // ADD / ASSIGN ADDRESS PAGE
    // ==========================================================

    @GetMapping("/{sellerId}/addresses/add")
    public String showAssignAddressPage(
            @PathVariable String sellerId,
            Model model) {

        Seller seller =
                sellerService.findSellerById(
                        sellerId);

        if (seller == null) {

            return "redirect:/admin/sellers";
        }


        List<Address> addresses =
                addressService.findAllAddresses();


        List<Address> sellerAddresses =
                sellerService.findAddressesBySeller(
                        sellerId);


        model.addAttribute(
                "seller",
                seller);

        model.addAttribute(
                "addresses",
                addresses);

        model.addAttribute(
                "sellerAddresses",
                sellerAddresses);


        return "address/assign-seller-address";
    }


    // ==========================================================
    // ASSIGN ADDRESS
    // ==========================================================

    @PostMapping("/{sellerId}/addresses/add")
    public String assignAddress(
            @PathVariable String sellerId,
            @RequestParam String addressId) {

        sellerService.assignAddressToSeller(
                sellerId,
                addressId);


        return "redirect:/seller/profile/"
                + sellerId;
    }


    // ==========================================================
    // REMOVE ADDRESS
    // ==========================================================

    @PostMapping(
            "/{sellerId}/addresses/remove/{addressId}")
    public String removeAddress(
            @PathVariable String sellerId,
            @PathVariable String addressId) {

        sellerService.removeAddressFromSeller(
                sellerId,
                addressId);


        return "redirect:/seller/profile/"
                + sellerId;
    }


    // ==========================================================
    // SELLER LIST
    // ==========================================================

    @GetMapping("/list")
    public String sellerList(
            Model model) {

        List<Seller> sellers =
                sellerService.findAllSellers();


        model.addAttribute(
                "sellers",
                sellers);


        return "seller/sellers";
    }
    
    @GetMapping("/dashboard")
    public String dashboard() {

        return "seller/dashboard";
    }
}