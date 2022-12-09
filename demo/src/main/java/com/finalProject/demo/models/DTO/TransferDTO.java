package com.finalProject.demo.models.DTO;

import java.math.BigDecimal;

public class TransferDTO {

    public static Long getReceiverAccountId;
    private String nameSendAccount;
    private static Long sendAccountId;
    private Long receiverAccountId;
    private BigDecimal amount;


    public TransferDTO(String nameSendAccount, Long receiverAccountId, BigDecimal amount) {
        this.nameSendAccount = nameSendAccount;
        this.receiverAccountId = receiverAccountId;
        this.amount = amount;
    }

    public static Long getGetReceiverAccountId() {
        return getReceiverAccountId;
    }

    public static void setGetReceiverAccountId(Long getReceiverAccountId) {
        TransferDTO.getReceiverAccountId = getReceiverAccountId;
    }

    public String getNameSendAccount() {
        return nameSendAccount;
    }

    public void setNameSendAccount(String nameSendAccount) {
        this.nameSendAccount = nameSendAccount;
    }

    public static Long getSendAccountId() {
        return sendAccountId;
    }

    public static void setSendAccountId(Long sendAccountId) {
        TransferDTO.sendAccountId = sendAccountId;
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
