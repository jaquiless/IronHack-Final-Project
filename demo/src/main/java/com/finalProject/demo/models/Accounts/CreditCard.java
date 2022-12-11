package com.finalProject.demo.models.Accounts;


import com.finalProject.demo.models.Users.AccountHolder;
import javax.persistence.Entity;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;


@Entity
public class CreditCard extends Account {
    @NotNull
    @DecimalMin(value = "100", inclusive = true)
    @DecimalMax(value = "100000", inclusive = true)
    private BigDecimal creditLimit = new BigDecimal("100").setScale(2, RoundingMode.HALF_DOWN);
    @NotNull
    @DecimalMin(value = "0.10", inclusive = true)
    @DecimalMax(value = "0.20", inclusive = true)
    private BigDecimal interestRate = new BigDecimal("0.20").setScale(2, RoundingMode.HALF_DOWN);

    private LocalDate lastUpdate = LocalDate.now();

    //Constructors

    public CreditCard() {
    }

    public CreditCard(AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        super(primaryOwner, secondaryOwner);
    }
    public CreditCard(AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal  creditLimit, BigDecimal interestRate) {
        super(primaryOwner, secondaryOwner);
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

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void checkInterests() {
        Period period = Period.between(lastUpdate, LocalDate.now());

        if (period.getMonths() == 1) {
            BigDecimal profit = super.getBalance().multiply(interestRate.divide(new BigDecimal(12)));
            super.setBalance(super.getBalance().add(profit).setScale(2, RoundingMode.HALF_DOWN));
            lastUpdate = LocalDate.now();
        }
    }

}
