package com.finalProject.demo.Controllers.Users.Interfaces;

import com.finalProject.demo.models.Accounts.*;
import com.finalProject.demo.models.DTO.AccountDTO;
import com.finalProject.demo.models.Users.ThirdParty;


public interface AdminControllerInt {


    Account addChecking (AccountDTO checking);

    Account addCreditCard (AccountDTO creditCard);

    Account addSavings (AccountDTO savings);

    ThirdParty addThirdParty (ThirdParty thirdParty);

    Account findAccountById (Long accountId);
}
