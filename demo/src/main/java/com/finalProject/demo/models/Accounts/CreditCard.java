package com.finalProject.demo.models.Accounts;


import com.finalProject.demo.models.Users.AccountHolder;
import javax.persistence.Entity;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;


@Entity
public class CreditCard extends Account {
    @Max(value = 100000)
    @Min(value = 100)
    private BigDecimal creditLimit;
    @DecimalMin(value = "0.1")
    private BigDecimal interestRate;

    //Constructors

    public CreditCard() {
    }

    public CreditCard(BigDecimal balance, Integer secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner,  BigDecimal creditLimit, BigDecimal interestRate) {
        super(balance, secretKey, primaryOwner, secondaryOwner );
        setCreditLimit(creditLimit);
        setInterestRate(interestRate);
    }

    //Getters & Setters

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        if(creditLimit == null) this.creditLimit=  BigDecimal.valueOf(100);
        else this.creditLimit = creditLimit;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        if(interestRate == null) this.interestRate = BigDecimal.valueOf(0.2);
        else this.interestRate = interestRate;
    }

}
