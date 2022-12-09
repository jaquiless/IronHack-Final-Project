package com.finalProject.demo.models.Accounts;

import com.finalProject.demo.models.Users.AccountHolder;
import com.finalProject.demo.models.Users.Status;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@DynamicUpdate
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "accounts")
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    private BigDecimal balance;
    private Integer secretKey;
    @ManyToOne
    private AccountHolder primaryOwner;
    @ManyToOne
    private AccountHolder secondaryOwner;
    private BigDecimal penaltyFee = BigDecimal.valueOf(40);
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    //Contructors
    public Account() {
    }

    public Account(BigDecimal balance, Integer secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        this.balance = balance;
        this.secretKey = secretKey;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;


    }


    //Getters & Setters
    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Integer getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(Integer secretKey) {
        this.secretKey = secretKey;
    }

    public AccountHolder getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(AccountHolder primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public AccountHolder getSecondaryOwner() {
        return secondaryOwner;
    }

    public void setSecondaryOwner(AccountHolder secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }

    public BigDecimal getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee(BigDecimal penaltyFee) {
        this.penaltyFee = penaltyFee;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}
