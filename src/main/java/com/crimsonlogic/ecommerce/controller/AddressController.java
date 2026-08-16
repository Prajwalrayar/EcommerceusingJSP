package com.crimsonlogic.ecommerce.controller;

import com.crimsonlogic.ecommerce.model.Address;
import com.crimsonlogic.ecommerce.service.AddressService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    /**
     * Displays all addresses.
     */
    @GetMapping("/list")
    public String viewAllAddresses(Model model) {

        List<Address> addresses =
                addressService.findAllAddresses();

        model.addAttribute("addresses", addresses);

        return "address/addresses";
    }

    /**
     * Displays add address page.
     */
    @GetMapping("/add")
    public String showAddAddressForm(Model model) {

        model.addAttribute("address", new Address());

        return "address/add-address";
    }

    /**
     * Inserts address.
     */
    @PostMapping("/add")
    public String addAddress(
            @ModelAttribute Address address) {

        addressService.insertAddress(address);

        return "redirect:/address/list";
    }

    /**
     * Displays edit address page.
     */
    @GetMapping("/edit/{addressId}")
    public String showEditAddressForm(
            @PathVariable String addressId,
            Model model) {

        Address address =
                addressService.findAddressById(addressId);

        model.addAttribute("address", address);

        return "address/edit-address";
    }

    /**
     * Updates address.
     */
    @PostMapping("/update")
    public String updateAddress(
            @ModelAttribute Address address) {

        addressService.updateAddress(address);

        return "redirect:/address/list";
    }

    /**
     * Deletes address.
     */
    @PostMapping("/delete/{addressId}")
    public String deleteAddress(
            @PathVariable String addressId) {

        addressService.deleteAddress(addressId);

        return "redirect:/address/list";
    }
}