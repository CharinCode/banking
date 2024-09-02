package com.example.banking.dto;

import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;


public record AccountDTO (
        Integer id,
        String accountName,
        BigDecimal balance
){}
