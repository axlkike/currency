package com.axlkike.currency.services;

/**
 * Created by kikejf on 19/01/2016.
 */
import com.axlkike.currency.domain.User;

public interface IuserService {

    User getUserByUserId(String userId);

    void saveUser(User user);
}