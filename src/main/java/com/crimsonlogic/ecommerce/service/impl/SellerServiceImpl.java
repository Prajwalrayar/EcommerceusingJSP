package com.crimsonlogic.ecommerce.service.impl;

import com.crimsonlogic.ecommerce.dao.SellerMapper;
import com.crimsonlogic.ecommerce.exception.ValidationException;
import com.crimsonlogic.ecommerce.model.Address;
import com.crimsonlogic.ecommerce.model.Seller;
import com.crimsonlogic.ecommerce.service.SellerService;
import com.crimsonlogic.ecommerce.service.abstraction.UserService;
import com.crimsonlogic.ecommerce.util.ValidationUtil;

import java.util.List;

/**
 * Seller service implementation.
 *
 * Reuses common user functionality through
 * UserService<Seller>.
 */
public class SellerServiceImpl
        extends UserService<Seller>
        implements SellerService {

    private SellerMapper sellerMapper;


    /**
     * Sets SellerMapper.
     *
     * @param sellerMapper SellerMapper
     */
    public void setSellerMapper(
            SellerMapper sellerMapper) {

        this.sellerMapper = sellerMapper;
    }


    /**
     * Inserts Seller.
     *
     * @param seller Seller
     */
    @Override
    public void insertSeller(Seller seller) {

        if (seller == null) {

            throw new ValidationException(
                    "Seller information is required.");
        }

        sellerMapper.insertSeller(seller);
    }


    /**
     * Updates Seller profile.
     *
     * Seller can edit:
     * - Name
     * - Email
     * - Phone
     * - Shop Name
     * - Shop Address
     *
     * @param seller Seller
     */
    @Override
    public void updateSeller(Seller seller) {

        if (seller == null) {

            throw new ValidationException(
                    "Seller information is required.");
        }


        /*
         * Reuse common User validation.
         */
        validateCommonProfile(seller);


        /*
         * Seller-specific validation.
         */
        ValidationUtil.validateShopName(
                seller.getShopName());

        ValidationUtil.validateShopAddress(
                seller.getShopAddress());


        sellerMapper.updateSeller(seller);
    }


    /**
     * Updates Seller password.
     *
     * @param userId User ID
     * @param userPassword Encrypted password
     */
    @Override
    public void updatePassword(
            String userId,
            String userPassword) {

        sellerMapper.updatePassword(
                userId,
                userPassword);
    }


    /**
     * Deletes Seller.
     *
     * @param sellerId Seller ID
     */
    @Override
    public void deleteSeller(
            String sellerId) {

        sellerMapper.deleteSeller(
                sellerId);
    }


    /**
     * Finds Seller by ID.
     *
     * @param sellerId Seller ID
     * @return Seller
     */
    @Override
    public Seller findSellerById(
            String sellerId) {

        return sellerMapper.findSellerById(
                sellerId);
    }


    /**
     * Finds Seller by email.
     *
     * @param email Email
     * @return Seller
     */
    @Override
    public Seller findSellerByEmail(
            String email) {

        return sellerMapper.findSellerByEmail(
                email);
    }


    /**
     * Finds Seller by phone.
     *
     * @param phone Phone number
     * @return Seller
     */
    @Override
    public Seller findSellerByPhone(
            String phone) {

        return sellerMapper.findSellerByPhone(
                phone);
    }


    /**
     * Returns all Sellers.
     *
     * @return Seller list
     */
    @Override
    public List<Seller> findAllSellers() {

        return sellerMapper.findAllSellers();
    }


    /**
     * Changes Seller password.
     *
     * Reuses password validation and
     * encryption from UserService.
     *
     * @param sellerId Seller ID
     * @param currentPassword Current password
     * @param newPassword New password
     * @param confirmPassword Confirm password
     */
    @Override
    public void changePassword(
            String sellerId,
            String currentPassword,
            String newPassword,
            String confirmPassword) {

        Seller seller =
                sellerMapper.findSellerById(
                        sellerId);


        if (seller == null) {

            throw new ValidationException(
                    "Seller not found.");
        }


        String encryptedPassword =
                generateNewPassword(
                        seller,
                        currentPassword,
                        newPassword,
                        confirmPassword);


        sellerMapper.updatePassword(
                sellerId,
                encryptedPassword);
    }

    @Override
    public void assignAddressToSeller(
            String sellerId,
            String addressId) {

        if (sellerId == null ||
                sellerId.trim().isEmpty()) {

            throw new ValidationException(
                    "Seller ID is required.");
        }

        if (addressId == null ||
                addressId.trim().isEmpty()) {

            throw new ValidationException(
                    "Address ID is required.");
        }

        sellerMapper.assignAddressToSeller(
                sellerId,
                addressId);
    }


    @Override
    public void removeAddressFromSeller(
            String sellerId,
            String addressId) {

        if (sellerId == null ||
                sellerId.trim().isEmpty()) {

            throw new ValidationException(
                    "Seller ID is required.");
        }

        if (addressId == null ||
                addressId.trim().isEmpty()) {

            throw new ValidationException(
                    "Address ID is required.");
        }

        sellerMapper.removeAddressFromSeller(
                sellerId,
                addressId);
    }


    @Override
    public List<Address> findAddressesBySeller(
            String sellerId) {

        return sellerMapper.findAddressesBySeller(
                sellerId);
    }
}