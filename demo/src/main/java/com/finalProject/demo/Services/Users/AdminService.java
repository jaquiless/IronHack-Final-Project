package com.finalProject.demo.Services.Users;

import com.finalProject.demo.Services.Users.Interfaces.AdminServiceInt;
import com.finalProject.demo.models.Accounts.*;
import com.finalProject.demo.models.DTO.AccountDTO;
import com.finalProject.demo.models.Users.AccountHolder;
import com.finalProject.demo.models.Users.Address;
import com.finalProject.demo.models.Users.Admin;
import com.finalProject.demo.models.Users.ThirdParty;
import com.finalProject.demo.repositories.Accounts.*;
import com.finalProject.demo.repositories.Users.AccountHolderRepository;
import com.finalProject.demo.repositories.Users.AdminRepository;
import com.finalProject.demo.repositories.Users.ThirdPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Service
public class AdminService implements AdminServiceInt {

    @Autowired
    AdminRepository adminRepository;
    @Autowired
    ChekingRepository chekingRepository;
    @Autowired
    SavingsRepository savingsRepository;
    @Autowired
    CreditCardRepository creditCardRepository;
    @Autowired
    StudentCheckingRepository studentCheckingRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    ThirdPartyRepository thirdPartyRepository;


    public Account addCheckingAccount(AccountDTO checkingDTO) {
        AccountHolder primaryOwner = accountHolderRepository.findById(checkingDTO.getPrimaryOwnerId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "The user does not exist"));
        AccountHolder secondaryOwner = null;
        if(checkingDTO.getSecondaryOnwerId() != null) secondaryOwner = accountHolderRepository.findById(checkingDTO.getSecondaryOnwerId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "The user does not exist"));

        Period date1 = Period.between(primaryOwner.getDateOfBirth(), LocalDate.now());
        if(date1.getYears()<24){
            Account studentChecking = new StudentChecking(checkingDTO.getBalance(), checkingDTO.getSecretKey(), primaryOwner, secondaryOwner, checkingDTO.getMinimumBalance());
            return accountRepository.save(studentChecking);
        }
        return chekingRepository.save(new Checking(checkingDTO.getBalance(), checkingDTO.getSecretKey(), primaryOwner, secondaryOwner));
    }

    public Account addCreditCardAccount(AccountDTO creditCardDTO) {
        AccountHolder primaryOwner = accountHolderRepository.findById(creditCardDTO.getPrimaryOwnerId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "The user does not exist"));
        AccountHolder secondaryOwner = null;
        if(creditCardDTO.getSecondaryOnwerId() != null) secondaryOwner = accountHolderRepository.findById(creditCardDTO.getSecondaryOnwerId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "The user does not exist"));
        return creditCardRepository.save(new CreditCard(creditCardDTO.getBalance(), creditCardDTO.getSecretKey(), primaryOwner, secondaryOwner, creditCardDTO.getCreditLimit(), creditCardDTO.getInterestRate()));
    }

    public Account addSavingsAccount(AccountDTO savingsDTO) {
        AccountHolder primaryOwner = accountHolderRepository.findById(savingsDTO.getPrimaryOwnerId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "The user does not exist"));
        AccountHolder secondaryOwner = null;
        if(savingsDTO.getSecondaryOnwerId() != null) secondaryOwner = accountHolderRepository.findById(savingsDTO.getSecondaryOnwerId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "The user does not exist"));
        return savingsRepository.save(new Savings(savingsDTO.getBalance(), savingsDTO.getSecretKey(), primaryOwner, secondaryOwner, savingsDTO.getMinimumBalance(), savingsDTO.getInterestRate()));
    }

    public ThirdParty addThirdParty(ThirdParty thirdParty) {
        return thirdPartyRepository.save(thirdParty);
    }

    public Account findAccountById(Long accountId) {
        if(accountRepository.findById(accountId).isPresent()){
            return accountRepository.findById(accountId).get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El id especificado no se encuentra en la base de datos");
    }

    public Account updateBalance(Long accountId, BigDecimal balance) {
            Account account = accountRepository.findById(accountId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El id especificado no se encuentra en la base de datos"));
            account.setBalance(balance);
            return accountRepository.save(account);
    }

    public void deleteAccount(Long accountId) {
        if(accountRepository.findById(accountId).isPresent()){
            accountRepository.deleteById(accountId);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El id especificado no se encuentra en la base de datos");
    }

}
