package com.jmp.cloud.service.repository;

import com.jmp.dto.User;

public class UserRepository extends CrudRepository<User, Integer> {
    @Override public User save(User entity) {
        return getTable().put(entity.getId(), entity);
    }
}
