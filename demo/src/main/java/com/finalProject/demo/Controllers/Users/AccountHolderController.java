package com.finalProject.demo.Controllers.Users;


import com.finalProject.demo.Services.Accounts.AccountService;
import com.finalProject.demo.Services.Users.AccountHolderService;
import com.finalProject.demo.models.Accounts.Account;
import com.finalProject.demo.models.DTO.AccountDTO;
import com.finalProject.demo.models.DTO.TransferDTO;
import com.finalProject.demo.models.Users.AccountHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/AccountHolders")
public class AccountHolderController {

    @Autowired
    AccountHolderService accountHolderService;
    @Autowired
    AccountService accountService;

    //Funcion para acceder al Balance de la propia cuenta del AccountHolder
    @GetMapping("/accountId/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public AccountHolder findAccountHolderById(@PathVariable Long accountId) {
        return accountHolderService.findAccountHolderById(accountId);
    }

    //Funcion para transferir balance a cualquier cuenta desde AccountHolder
    @PostMapping("/transactBalanceByAccountHolder")
    @ResponseStatus(HttpStatus.OK)
    public Account transactBalanceAccountHolder(@RequestBody TransferDTO transferDTO) {
        return accountHolderService.transactBalance(transferDTO);
    }

}
