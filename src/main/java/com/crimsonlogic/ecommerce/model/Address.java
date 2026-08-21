package com.crimsonlogic.ecommerce.model;

import java.util.Objects;

/**
 * Represents an Address in the ecommerce application.
 *
 * This class contains the address information associated with
 * a customer, including house number, street, city, state,
 * country, and postal code.
 *
 * The class provides constructors, getters, setters, and
 * equality methods for managing address data.
 */
public class Address {

    /**
     * Unique identifier of the address.
     *
     * This value is used to identify a specific address
     * in the application.
     */
    private String addressId;

    /**
     * House or flat number of the address.
     *
     * Examples include 45, B-102, or Flat-203.
     */
    private String houseNumber;

    /**
     * Street name of the address.
     */
    private String street;

    /**
     * City in which the address is located.
     */
    private String city;

    /**
     * State in which the address is located.
     */
    private String state;

    /**
     * Country in which the address is located.
     */
    private String country;

    /**
     * Postal or ZIP code of the address.
     */
    private String zipCode;

    /**
     * Default constructor required by MyBatis.
     *
     * MyBatis uses the no-argument constructor when creating
     * and mapping Address objects from database results.
     */
    public Address() {

    }

    /**
     * Creates an Address object using the supplied address details.
     *
     * @param addressId unique identifier of the address
     * @param houseNumber house or flat number of the address
     * @param street street name of the address
     * @param city city in which the address is located
     * @param state state in which the address is located
     * @param country country in which the address is located
     * @param zipCode postal or ZIP code of the address
     */
    public Address(
            String addressId,
            String houseNumber,
            String street,
            String city,
            String state,
            String country,
            String zipCode) {

        this.addressId = addressId;
        this.houseNumber = houseNumber;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
    }

    // ===========================
    // Getters
    // ===========================

    /**
     * Returns the unique identifier of the address.
     *
     * @return address ID
     */
    public String getAddressId() {

        return addressId;
    }

    /**
     * Returns the house or flat number of the address.
     *
     * @return house number
     */
    public String getHouseNumber() {

        return houseNumber;
    }

    /**
     * Returns the street name of the address.
     *
     * @return street name
     */
    public String getStreet() {

        return street;
    }

    /**
     * Returns the city of the address.
     *
     * @return city name
     */
    public String getCity() {

        return city;
    }

    /**
     * Returns the state of the address.
     *
     * @return state name
     */
    public String getState() {

        return state;
    }

    /**
     * Returns the country of the address.
     *
     * @return country name
     */
    public String getCountry() {

        return country;
    }

    /**
     * Returns the postal or ZIP code of the address.
     *
     * @return postal or ZIP code
     */
    public String getZipCode() {

        return zipCode;
    }

    // ===========================
    // Setters
    // ===========================

    /**
     * Updates the unique identifier of the address.
     *
     * @param addressId new address ID
     */
    public void setAddressId(String addressId) {

        this.addressId = addressId;
    }

    /**
     * Updates the house or flat number of the address.
     *
     * @param houseNumber new house or flat number
     */
    public void setHouseNumber(String houseNumber) {

        this.houseNumber = houseNumber;
    }

    /**
     * Updates the street name of the address.
     *
     * @param street new street name
     */
    public void setStreet(String street) {

        this.street = street;
    }

    /**
     * Updates the city of the address.
     *
     * @param city new city name
     */
    public void setCity(String city) {

        this.city = city;
    }

    /**
     * Updates the state of the address.
     *
     * @param state new state name
     */
    public void setState(String state) {

        this.state = state;
    }

    /**
     * Updates the country of the address.
     *
     * @param country new country name
     */
    public void setCountry(String country) {

        this.country = country;
    }

    /**
     * Updates the postal or ZIP code of the address.
     *
     * @param zipCode new postal or ZIP code
     */
    public void setZipCode(String zipCode) {

        this.zipCode = zipCode;
    }


    /**
     * Generates a hash code based on all address fields.
     *
     * The hash code is consistent with the equals method and allows
     * Address objects to be used correctly in hash-based collections.
     *
     * @return hash code calculated from the address fields
     */
    @Override
    public int hashCode() {

        return Objects.hash(addressId, city, country, houseNumber, state, street, zipCode);
    }

    /**
     * Compares this Address with another object for equality.
     *
     * Two Address objects are considered equal when they belong
     * to the same class and contain equal values for all address fields.
     *
     * @param obj object to compare with this Address
     * @return true when both objects contain the same address information;
     *         otherwise false
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        Address other = (Address) obj;

        return Objects.equals(addressId, other.addressId) && Objects.equals(city, other.city)
                && Objects.equals(country, other.country) && Objects.equals(houseNumber, other.houseNumber)
                && Objects.equals(state, other.state) && Objects.equals(street, other.street)
                && Objects.equals(zipCode, other.zipCode);
    }

    /**
     * Returns a formatted string representation of the address.
     *
     * The address components are combined into a readable format
     * containing the house number, street, city, state, country,
     * and postal code.
     *
     * @return formatted address string
     */
    @Override
    public String toString() {

        return houseNumber + ", "
                + street + ", "
                + city + ", "
                + state + ", "
                + country + " - "
                + zipCode;
    }

}