package com.example.banking.service;

import com.example.banking.dto.AccountDTO;
import com.example.banking.dto.AccountReq;

import java.math.BigDecimal;
import java.util.Objects;

public interface AccountService {
    AccountDTO createAccount(AccountReq body);

    String deposit(AccountReq body);

    String withdraw (Integer id, BigDecimal money);
}
