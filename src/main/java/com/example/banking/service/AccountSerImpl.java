package com.example.banking.service;

import com.example.banking.dto.AccountDTO;
import com.example.banking.dto.AccountReq;
import com.example.banking.model.AccountBank;
import com.example.banking.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class AccountSerImpl implements AccountService{

    public AccountSerImpl(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    private final AccountRepo accountRepo;

    @Override
    public AccountDTO createAccount(AccountReq body) {
       var existAccount = accountRepo.findByAccountName(body.accountName());
       if (!existAccount.isEmpty()){
           throw new RuntimeException("Account exits");
       }

        var balance = body.balance();
        if (balance.intValue() < 0){
            throw new RuntimeException("Please do not enter negative balance.");
        }
        var papareAccount = new AccountBank().setAccountName(body.accountName()).setBalance(body.balance());
        var newAccount  = accountRepo.save(papareAccount);
        return new AccountDTO(newAccount.getId(), newAccount.getAccountName(), newAccount.getBalance());
    }

    @Override
    public String deposit(AccountReq body) {
        if (body.balance().intValue() < 0){
            throw new RuntimeException("Please do not enter negative balance.");
        }
      var depositAccount=  accountRepo.findByAccountName(body.accountName())
              .orElseThrow(()-> new RuntimeException("Not found Account!"));
      depositAccount.setBalance(BigDecimal.valueOf((depositAccount.getBalance().intValue()) + body.balance().intValue()));
      accountRepo.save(depositAccount);
      return "Deposit successful";
    }

    @Override
    public String withdraw(Integer id, BigDecimal money) {
        if (money.intValue() < 0){
            throw new RuntimeException("Please do not enter negative balance.");
        }
        var withdrawAccount =  accountRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Not found Account!"));
        if (withdrawAccount.getBalance().intValue() < money.intValue()){
            throw new RuntimeException("Insufficient funds");
        }
        withdrawAccount.setBalance(BigDecimal.valueOf((withdrawAccount.getBalance().intValue()) - money.intValue()));
        accountRepo.save(withdrawAccount);
        return "Withdrawal successful";
    }
}
