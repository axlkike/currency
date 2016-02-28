package com.axlkike.currency.services;

/**
 * Created by kikejf on 19/01/2016.
 */
import com.axlkike.currency.domain.User;
import com.axlkike.currency.domain.UserRoleType;
import com.axlkike.currency.repositories.UserRepository;
import org.joda.time.DateTime;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class UserServiceImpl implements IuserService {

    @Inject
    private UserRepository userRepository;


    @Override
    public User getUserByUserId(String userId) {
        User result = null;
        List<User> userList = userRepository.findByUserId(userId);
        if (userList != null && userList.size() > 0) {
            result = userRepository.findByUserId(userId).get(0);
        }
        return result;
    }

    @Override
    public void saveUser(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(UserRoleType.USER.toString());
        user.setRegisterDate(new DateTime());
        userRepository.save(user);
    }
}