package com.jmp.service.api;

import com.jmp.dto.BankCard;
import com.jmp.dto.Subscription;
import com.jmp.dto.User;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

public interface Service {
    void subscribe(BankCard bankCard);

    Optional<Subscription> getSubscriptionByBankCardNumber(String bankCardNumber);

    List<User> getAllUsers();

    public default double getAverageUsersAge() {
        return getAllUsers().stream().mapToLong(user -> ChronoUnit.YEARS.between(user.getBirthday(), LocalDate.now()))
            .average().orElse(Double.NaN);
    }

    public static boolean isPayableUser(User user) {
        return ChronoUnit.YEARS.between(user.getBirthday(), LocalDate.now()) > 18;
    };
}
