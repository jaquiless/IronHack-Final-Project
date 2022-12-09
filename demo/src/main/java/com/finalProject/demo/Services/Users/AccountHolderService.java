package com.finalProject.demo.Services.Users;

import com.finalProject.demo.Services.Accounts.Interfaces.AccountServiceInt;
import com.finalProject.demo.models.Accounts.Account;
import com.finalProject.demo.models.Accounts.Checking;
import com.finalProject.demo.models.Accounts.Savings;
import com.finalProject.demo.models.DTO.TransferDTO;
import com.finalProject.demo.models.Users.AccountHolder;
import com.finalProject.demo.repositories.Accounts.AccountRepository;
import com.finalProject.demo.repositories.Users.AccountHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AccountHolderService implements AccountServiceInt {

    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    AccountRepository accountRepository;


    public AccountHolder findAccountHolderById(Long accountId) {
        if(accountHolderRepository.findById(accountId).isPresent()){
            return accountHolderRepository.findById(accountId).get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El id especificado no se encuentra en la base de datos");
    }

    public Account transactBalance(TransferDTO transferDTO) {
        Account sendingAccount = accountRepository.findById(TransferDTO.getSendAccountId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "The user does not exist"));
        Account receivingAccount = accountRepository.findById(TransferDTO.getReceiverAccountId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "The user does not exist"));

        if(sendingAccount.getBalance().compareTo(transferDTO.getAmount()) > 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Not enough founds");
        if(transferDTO.getNameSendAccount().equals(receivingAccount.getPrimaryOwner().getName()) ||
                transferDTO.getNameSendAccount().equals(receivingAccount.getSecondaryOwner().getName())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Not enough founds");

        if(sendingAccount instanceof Checking){
            Checking checking = (Checking) sendingAccount;
            if(checking.getBalance().compareTo(checking.getMinimumBalance()) > 0){
                checking.setBalance(checking.getBalance().subtract(checking.getPenaltyFee()));
                return accountRepository.save(checking);
            } else if (sendingAccount instanceof Savings) {
                Savings savings = (Savings) sendingAccount;
                if(savings.getBalance().compareTo(savings.getMinimumBalance()) > 0){
                    savings.setBalance(savings.getBalance().subtract(savings.getPenaltyFee()));
                    return accountRepository.save(savings);
                }
            }
        }
        sendingAccount.setBalance(sendingAccount.getBalance());
        receivingAccount.setBalance(receivingAccount.getBalance());
        accountRepository.save(receivingAccount);
        accountRepository.save(sendingAccount);
        return accountRepository.save(sendingAccount);
    }
}
