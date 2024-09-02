package com.example.banking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AccountReq(
     @NotBlank String accountName,
     @NotNull BigDecimal balance
) {
}
