package com.finalProject.demo.models.Users;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.finalProject.demo.models.Accounts.Account;
import javax.persistence.*;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AccountHolder extends User {
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Past
    private LocalDate dateOfBirth;

    @Embedded
    private Address postalAddress;

    private String mailingAddress;

    //Relacion
    @OneToMany(mappedBy = "primaryOwner")
    private List<Account> accountListPrimary = new ArrayList<>();

    @OneToMany(mappedBy = "secondaryOwner")
    private List<Account> accountListSecondary = new ArrayList<>();

    //Constructors
    public AccountHolder() {
    }

    public AccountHolder(String name, LocalDate dateOfBirth, Address postalAddress, String mailingAddress) {
        super(name);
        this.dateOfBirth = dateOfBirth;
        this.postalAddress = postalAddress;
        this.mailingAddress = mailingAddress;
    }

    //Getters & Setters
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(Address postalAddress) {
        this.postalAddress = postalAddress;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public List<Account> getAccountListPrimary() {
        return accountListPrimary;
    }

    public void setAccountListPrimary(List<Account> accountListPrimary) {
        this.accountListPrimary = accountListPrimary;
    }

    public List<Account> getAccountListSecondary() {
        return accountListSecondary;
    }

    public void setAccountListSecondary(List<Account> accountListSecondary) {
        this.accountListSecondary = accountListSecondary;
    }
}
