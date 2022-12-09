package com.finalProject.demo.models.Users;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String address;
    private String postalCode;
    private String city;
    private String country;


    //Constructors

    public Address() {
    }

    public Address(String address, String postalCode, String city, String country) {
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }

    //Getters & Setters

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


}
