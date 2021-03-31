package com.jmp.cloud.bank.impl;

import com.jmp.bank.api.Bank;
import com.jmp.dto.*;

public class BankImpl implements Bank {
    @Override public BankCard createBankCard(User user, BankCardType bankCardType) {
        if (bankCardType == null) {
            return null;
        }
        switch (bankCardType) {
            case DEBIT:
                return new DebitBankCard(user);
            case CREDIT:
                return new CreditBankCard(user);
            default:
                throw new IllegalStateException("Unexpected value: " + bankCardType);
        }
    }
}
