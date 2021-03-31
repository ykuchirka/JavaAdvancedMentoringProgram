package com.jmp.cloud.service.impl;

import com.jmp.cloud.service.repository.CrudRepository;
import com.jmp.cloud.service.repository.SubscriptionRepository;
import com.jmp.cloud.service.repository.UserRepository;
import com.jmp.dto.BankCard;
import com.jmp.dto.Subscription;
import com.jmp.dto.User;
import com.jmp.service.api.Service;

import java.util.List;
import java.util.Optional;

import static java.time.LocalDate.now;

public class ServiceImpl implements Service {
    SubscriptionRepository subscriptionRepository = new SubscriptionRepository();
    UserRepository userRepository = new UserRepository();

    @Override public void subscribe(BankCard bankCard) {
        subscriptionRepository.save(new Subscription(bankCard.getNumber(), now()));
        userRepository.save(bankCard.getUser());
    }

    @Override public Optional<Subscription> getSubscriptionByBankCardNumber(String bankCardNumber) {
        return Optional.ofNullable(subscriptionRepository.findOne(bankCardNumber));
    }

    @Override public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
