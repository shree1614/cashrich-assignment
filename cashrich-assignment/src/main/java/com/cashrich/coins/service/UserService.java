package com.cashrich.coins.service;

import com.cashrich.coins.entity.User;
import com.cashrich.coins.entity.UserHistory;
import com.cashrich.coins.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    };

    public void registerUser(User user) {
        User isUser = userRepository.findByUsername(user.getUserName());
        if(isUser != null) {
            throw new IllegalStateException("Username already taken");
        }
        userRepository.save(user);
    }

    public User getUser(Long id) {
        User user = userRepository.findById(id);
        if(user == null) {
            throw new IllegalStateException("Can't find user");
        }

        return user;
    }

    public User loginUser(String userName, String password) {
        User isUser = userRepository.findByUsername(userName);
        if(isUser != null && isUser.getUserName().equals(userName) && isUser.checkPassword(password)) {
            return isUser;
        }

        throw new IllegalStateException("Username or Password doesn't match");
    }

    public void saveUserHistory(Long userId) {
        User user = userRepository.findById(userId);
        if(user == null) {
            throw new IllegalStateException("Can't find user");
        }
        userRepository.saveUserHistory(user);
    }
}
