package com.finalProject.demo.models.Accounts;


import com.finalProject.demo.models.Users.AccountHolder;
import com.finalProject.demo.models.Users.Status;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Savings extends Account {

    @Min(value = 100)
    private BigDecimal minimumBalance = BigDecimal.valueOf(1000);
    private LocalDate creationDate = LocalDate.now();

    @DecimalMax(value = "0.5")
    private BigDecimal interestRate = BigDecimal.valueOf(0.0025);

    //Constructors

    public Savings() {
    }

    public Savings(BigDecimal balance, Integer secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal minimumBalance, BigDecimal interestRate) {
        super(balance, secretKey, primaryOwner, secondaryOwner);
        this.minimumBalance = minimumBalance;
        this.interestRate = interestRate;
    }

    //Getters & Setters

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
}
