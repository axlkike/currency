package com.axlkike.currency.repositories;

import com.axlkike.currency.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * user repo
 */
public interface UserRepository extends CrudRepository<User,Integer> {
    List<User> findByUserId(String userId);
}
