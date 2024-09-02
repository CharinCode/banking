package com.example.banking.repository;

import com.example.banking.model.AccountBank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepo extends JpaRepository<AccountBank,Integer> {
   Optional<AccountBank> findByAccountName(String accountName);
}
