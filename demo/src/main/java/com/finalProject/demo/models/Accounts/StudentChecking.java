package com.finalProject.demo.models.Accounts;

import com.finalProject.demo.models.Users.AccountHolder;
import com.finalProject.demo.models.Users.Status;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class StudentChecking extends Account {

    private BigDecimal minimumBalance = BigDecimal.valueOf(250);
    private LocalDate creationDate = LocalDate.now();

    //Constructors

    public StudentChecking() {
    }

    public StudentChecking(AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey) {
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
}



