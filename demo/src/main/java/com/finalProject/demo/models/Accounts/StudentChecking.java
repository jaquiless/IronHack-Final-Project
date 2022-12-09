package com.finalProject.demo.models.Accounts;

import com.finalProject.demo.models.Users.AccountHolder;
import com.finalProject.demo.models.Users.Status;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class StudentChecking extends Account {

    private BigDecimal minimumBalance;
    private Date creationDate;

    //Constructors

    public StudentChecking() {
    }

    public StudentChecking(BigDecimal balance, Integer secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal minimumBalance) {
        super(balance, secretKey, primaryOwner, secondaryOwner);
        this.minimumBalance = minimumBalance;
        this.creationDate = creationDate;
    }

    //Getters & Setters

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}



