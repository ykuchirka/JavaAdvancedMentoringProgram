package com.jmp.application;

import com.jmp.bank.api.Bank;
import com.jmp.cloud.bank.impl.BankImpl;
import com.jmp.cloud.service.impl.ServiceImpl;
import com.jmp.dto.BankCard;
import com.jmp.dto.BankCardType;
import com.jmp.dto.User;
import com.jmp.service.api.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

public class Test {
    private static final Bank bank = new BankImpl();
    private static final Service service = new ServiceImpl();

    private static final Function<Integer, BankCard> bankCardCreator = id -> {
        var bankCard = bank.createBankCard(userGenerate(id), id % 2 == 0 ? BankCardType.CREDIT : BankCardType.DEBIT);
        bankCard.setNumber(String.format("4001 5638 3245 %04d", id));
        return bankCard;
    };

    private static final Consumer<BankCard> subscriptor = service::subscribe;

    private static User userGenerate(Integer id) {
        var surname = id % 2 == 0 ? "Petrov" : "Sidorov";
        var name = id > 2 ? "Oleg" : "Ivan";
        var birthday = LocalDate.of(1980 + 7 * id, Month.JANUARY, 1 + 3 * id);
        return new User(id, name, surname, birthday);
    }

    private static final Consumer<BankCard> bankCardPrinter = card -> {
        System.out.printf("Card with № %s belongs %s his birthday %s%n", card.getNumber(),
            card.getUser().getFullName(), card.getUser().getBirthday());
        System.out.printf("Older 18 -> %s%n", Service.isPayableUser(card.getUser()));
    };

    public static void testBankRunner() {
        testCreateBankCard();
        testServiceSubscribe();
    }

    private static void testCreateBankCard() {
        Stream.of(1, 2, 3, 4).map(bankCardCreator).forEach(bankCardPrinter);
    }

    private static void testServiceSubscribe() {
        Stream.of(1, 2, 3, 4).map(bankCardCreator).forEach(subscriptor);
        System.out.printf("Average users age %s%n", service.getAverageUsersAge());
    }

}
