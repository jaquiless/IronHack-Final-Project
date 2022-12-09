package com.finalProject.demo.Controllers.Users.Interfaces;

import com.finalProject.demo.models.Accounts.Account;
import com.finalProject.demo.models.DTO.AccountDTO;
import com.finalProject.demo.models.Users.AccountHolder;

public interface AccountHolderControllerInt {
    AccountHolder findOwnAccountHolderById (Long accountId);

    AccountHolder sendBalance (AccountDTO accountHolder);
}
