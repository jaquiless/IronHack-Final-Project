package com.finalProject.demo.models.DTO;

import java.math.BigDecimal;

public class AccountDTO {

    private BigDecimal balance;
    private BigDecimal minimumBalance;
    private Integer secretKey;
    private Long primaryOwnerId;
    private Long secondaryOnwerId;
    private BigDecimal creditLimit;
    private BigDecimal interestRate;


    public AccountDTO(BigDecimal balance, BigDecimal minimumBalance, Integer secretKey, Long primaryOwnerId, Long secondaryOnwerId, BigDecimal creditLimit, BigDecimal interestRate) {
        this.balance = balance;
        this.minimumBalance = minimumBalance;
        this.secretKey = secretKey;
        this.primaryOwnerId = primaryOwnerId;
        this.secondaryOnwerId = secondaryOnwerId;
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public Integer getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(Integer secretKey) {
        this.secretKey = secretKey;
    }

    public Long getPrimaryOwnerId() {
        return primaryOwnerId;
    }

    public void setPrimaryOwnerId(Long primaryOwnerId) {
        this.primaryOwnerId = primaryOwnerId;
    }

    public Long getSecondaryOnwerId() {
        return secondaryOnwerId;
    }

    public void setSecondaryOnwerId(Long secondaryOnwerId) {
        this.secondaryOnwerId = secondaryOnwerId;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
}
