package com.example.banking;

public interface BankService {
    void transferMoney(int fromAccountId, int toAccountId, double amount);
}
