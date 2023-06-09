package com.hotel.service;

import com.hotel.domain.User;
import com.hotel.repository.UserRepository;
import com.hotel.exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public boolean existsByLogin(String login) {
        return userRepository.existsByLogin(login);
    }

    public User loadUserByLogin(String login) {
        return (User) userRepository.findByLogin(login).orElseThrow(() -> new ResourceNotFoundException("User", "username", login));
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }
}