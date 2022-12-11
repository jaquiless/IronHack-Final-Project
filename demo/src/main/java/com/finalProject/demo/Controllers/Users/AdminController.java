package com.finalProject.demo.Controllers.Users;


import com.finalProject.demo.Controllers.Users.Interfaces.AdminControllerInt;
import com.finalProject.demo.Services.Users.AdminService;
import com.finalProject.demo.models.Accounts.*;
import com.finalProject.demo.models.DTO.AccountDTO;
import com.finalProject.demo.models.Users.Admin;
import com.finalProject.demo.models.Users.ThirdParty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/Admins")
public class AdminController implements AdminControllerInt {

    @Autowired
    AdminService adminService;


    // Funciones de crear Accounts desde Admin

    @PostMapping("/addCheckingAccount")
    @ResponseStatus(HttpStatus.CREATED)
    public Account  addChecking(@RequestBody AccountDTO checking) {
        return adminService.addCheckingAccount(checking);
    }

    @PostMapping("/addCreditCardAccount")
    @ResponseStatus(HttpStatus.CREATED)
    public Account addCreditCard(@RequestBody AccountDTO creditCard) {
        return adminService.addCreditCardAccount(creditCard);
    }

    @PostMapping("/addSavingsAccount")
    @ResponseStatus(HttpStatus.CREATED)
    public Account addSavings(@RequestBody AccountDTO savings) {
        return adminService.addSavingsAccount(savings);
    }


    @PostMapping("/addThirdParty")
    public ThirdParty addThirdParty(@RequestBody ThirdParty thirdParty) {
        return adminService.addThirdParty(thirdParty);
    }

    //Funcion para acceder al balance de las Accounts desde Admin

    @GetMapping("/accountId/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public Account findAccountById(@PathVariable Long accountId) {
        return adminService.findAccountById(accountId);
    }

    //Funcion para modificar el balance de las Accounts desde Admin
    @PatchMapping("/accountId/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public Account updateBalance(@PathVariable Long accountId, @RequestParam BigDecimal balance){
        return adminService.updateBalance(accountId, balance);
    }

    //Funcion para borrar Account desde Admin
    @DeleteMapping("/accountId/{accountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable Long accountId){
         adminService.deleteAccount(accountId);
    }
}
