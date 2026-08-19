package com.crimsonlogic.ecommerce.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crimsonlogic.ecommerce.model.Address;
import com.crimsonlogic.ecommerce.service.AddressService;
import com.crimsonlogic.ecommerce.service.CustomerService;
import com.crimsonlogic.ecommerce.util.IdGenerator;

/**
 * Controller responsible for handling address-related requests.
 *
 * The controller receives HTTP requests for creating, retrieving,
 * updating, and deleting addresses and delegates the actual
 * processing to AddressService.
 *
 * AddressController follows the MVC pattern by preparing data
 * in the Model and returning the appropriate view name.
 */
@Controller
@RequestMapping("/address")
public class AddressController {

    /**
     * Service used to perform address-related business operations.
     *
     * The controller delegates address processing to the service
     * layer instead of directly accessing the database.
     */
    private final AddressService addressService;
    
    private final CustomerService customerService;

    /**
     * Creates the AddressController with its required service dependency.
     *
     * Constructor injection allows Spring to provide the AddressService
     * instance required for address-related operations.
     *
     * @param addressService service used to perform address operations
     */
    public AddressController(AddressService addressService,
    		CustomerService customerService) {
        this.addressService = addressService;
        this.customerService = customerService;
        
    }

    /**
     * Displays all addresses.
     *
     * The controller retrieves all addresses from the service layer,
     * adds them to the Model, and returns the address list view.
     *
     * HTTP method: GET
     * Endpoint: /address/list
     *
     * @param model model used to pass address data to the view
     * @return address list view
     */
    @GetMapping("/list")
    public String viewAllAddresses(Model model) {

        // Retrieve all addresses through the service layer.
        List<Address> addresses =
                addressService.findAllAddresses();

        // Make the retrieved addresses available to the JSP view.
        model.addAttribute("addresses", addresses);

        return "address/addresses";
    }

    /**
     * Displays the page used to add a new address.
     *
     * A new Address object is added to the Model so that the
     * form can bind its input fields to the Address object.
     *
     * HTTP method: GET
     * Endpoint: /address/add
     *
     * @param model model used to provide the new Address object to the form
     * @return add address view
     */
    @GetMapping("/add")
    public String showAddAddressForm(Model model) {

        // Provide an empty Address object for form data binding.
        model.addAttribute("address", new Address());

        return "address/add-address";
    }
    
    @GetMapping("/customer/{customerId}/add")
    public String showCustomerAddAddressForm(
            @PathVariable String customerId,
            Model model) {

        model.addAttribute("address", new Address());
        model.addAttribute("customerId", customerId);

        return "address/add-address";
    }
    
    @PostMapping("/customer/{customerId}/add")
    public String addCustomerAddress(
            @PathVariable String customerId,
            @ModelAttribute Address address) {

        // Generate Address ID.
        String addressId =
                IdGenerator.generateId("ADDR");

        address.setAddressId(addressId);

        // 1. Insert into address table.
        addressService.insertAddress(address);

        // 2. Insert customer_id + address_id
        //    into customer_address table.
        customerService.assignAddress(
                customerId,
                addressId
        );

        // 3. Return directly to checkout.
        return "redirect:/checkout/" + customerId;
    }

    /**
     * Inserts a new address.
     *
     * The submitted form data is bound to the Address object and
     * passed to the service layer for persistence.
     *
     * HTTP method: POST
     * Endpoint: /address/add
     *
     * @param address address information submitted from the form
     * @return redirect to the address list after successful insertion
     */
    @PostMapping("/add")
    public String addAddress(
            @ModelAttribute Address address) {

        // Generate address ID before inserting.
        address.setAddressId(
                IdGenerator.generateId("ADDR")
        );

        // Insert address into address table.
        addressService.insertAddress(address);

        return "redirect:/address/list";
    }

    /**
     * Displays the page used to edit an existing address.
     *
     * The address ID is obtained from the URL and used to retrieve
     * the corresponding address before displaying the edit form.
     *
     * HTTP method: GET
     * Endpoint: /address/edit/{addressId}
     *
     * @param addressId address ID supplied in the URL
     * @param model model used to pass the existing address to the view
     * @return edit address view
     */
    @GetMapping("/edit/{addressId}")
    public String showEditAddressForm(
            @PathVariable String addressId,
            Model model) {

        // Retrieve the existing address using the supplied address ID.
        Address address =
                addressService.findAddressById(addressId);

        // Make the existing address available to the edit form.
        model.addAttribute("address", address);

        return "address/edit-address";
    }

    /**
     * Updates an existing address.
     *
     * The submitted form data is bound to the Address object and
     * passed to the service layer for updating the existing record.
     *
     * HTTP method: POST
     * Endpoint: /address/update
     *
     * @param address updated address information submitted from the form
     * @return redirect to the address list after successful update
     */
    @PostMapping("/update")
    public String updateAddress(
            @ModelAttribute Address address) {

        // Delegate address update processing to the service layer.
        addressService.updateAddress(address);

        // Redirect to the address list to display the updated information.
        return "redirect:/address/list";
    }

    /**
     * Deletes an existing address.
     *
     * The address ID is obtained from the URL and passed to the
     * service layer so that the corresponding address can be deleted.
     *
     * HTTP method: POST
     * Endpoint: /address/delete/{addressId}
     *
     * @param addressId address ID supplied in the URL
     * @return redirect to the address list after successful deletion
     */
    @PostMapping("/delete/{addressId}")
    public String deleteAddress(
            @PathVariable String addressId) {

        // Delegate address deletion to the service layer.
        addressService.deleteAddress(addressId);

        // Redirect to the address list after deletion.
        return "redirect:/address/list";
    }
}