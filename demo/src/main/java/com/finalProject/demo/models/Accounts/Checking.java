package com.finalProject.demo.models.Accounts;

import com.finalProject.demo.models.Users.AccountHolder;
import com.finalProject.demo.models.Users.Status;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Date;


@Entity
public class Checking extends Account {
    private BigDecimal minimumBalance = BigDecimal.valueOf(250).setScale(2, RoundingMode.HALF_DOWN);;
    private BigDecimal monthlyMaintenanceFee = BigDecimal.valueOf(12).setScale(2, RoundingMode.HALF_DOWN);
    private LocalDate creationDate = LocalDate.now();

    //Constructors

    public Checking() {
    }

    public Checking(AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey) {
        super(primaryOwner, secondaryOwner);
        setSecretKey(secretKey);
    }

    //Getters & Setters

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public BigDecimal getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public void setMonthlyMaintenanceFee(BigDecimal monthlyMaintenanceFee) {
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }


}
