package com.finalProject.demo.models.DTO;

import java.math.BigDecimal;

public class TransferDTO {

    private BigDecimal amount;
    private  Long sendAccountId;
    private String nameSendAccount;
    private Long receiverAccountId;


    public TransferDTO(BigDecimal amount, Long sendAccountId, String nameSendAccount, Long receiverAccountId) {
        this.amount = amount;
        this.sendAccountId = sendAccountId;
        this.nameSendAccount = nameSendAccount;
        this.receiverAccountId = receiverAccountId;
    }

    public TransferDTO(BigDecimal amount, Long sendAccountId, Long receiverAccountId) {
        this.amount = amount;
        this.sendAccountId = sendAccountId;
        this.receiverAccountId = receiverAccountId;
    }

    public String getNameSendAccount() {
        return nameSendAccount;
    }

    public void setNameSendAccount(String nameSendAccount) {
        this.nameSendAccount = nameSendAccount;
    }

    public Long getSendAccountId() {
        return sendAccountId;
    }

    public void setSendAccountId(Long sendAccountId) {
        this.sendAccountId = sendAccountId;
    }

    public Long getReceiverAccountId() {
        return receiverAccountId;
    }

    public void setReceiverAccountId(Long receiverAccountId) {
        this.receiverAccountId = receiverAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
