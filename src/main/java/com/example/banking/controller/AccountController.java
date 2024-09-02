package com.example.banking.controller;

import com.example.banking.dto.AccountDTO;
import com.example.banking.dto.AccountReq;
import com.example.banking.model.AccountBank;
import com.example.banking.repository.AccountRepo;
import com.example.banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/banking")
public class AccountController {
    private final AccountService accountService;
    private  final AccountRepo accountRepo;

    public AccountController(AccountService accountService, AccountRepo accountRepo) {
        this.accountService = accountService;
        this.accountRepo = accountRepo;
    }


    @GetMapping("/{id}")
    public Optional<AccountBank> getAccountById(
            @PathVariable("id") Integer id
    ){
        return accountRepo.findById(id);
    }

    @GetMapping
    public List<AccountBank> getAccounts(){
        return  accountRepo.findAll();
    }

    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountReq body){
        return new ResponseEntity<>(accountService.createAccount(body), HttpStatus.CREATED);
    }

    @PatchMapping("")
    public  ResponseEntity<String> deposit(@RequestBody AccountReq body){
        var result = accountService.deposit(body);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{id}")
    public  ResponseEntity<String> withdrawal(
            @PathVariable("id") Integer id,
            @RequestParam("money") BigDecimal money
            ){

      var result =  accountService.withdraw(id,money);
      return ResponseEntity.ok(result);

    }
}
