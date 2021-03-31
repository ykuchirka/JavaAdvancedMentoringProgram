package com.jmp.cloud.service.repository;

import com.jmp.dto.Subscription;


public class SubscriptionRepository extends CrudRepository<Subscription, String> {

    @Override public Subscription save(Subscription entity) {
        return getTable().put(entity.getBankcard(), entity);
    }

}
