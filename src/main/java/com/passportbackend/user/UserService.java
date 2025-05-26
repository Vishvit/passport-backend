package com.passportbackend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User newUser) {
        return userRepository.save(newUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findUser(username);
    }

    public User findUser(String username) {
        return userRepository.findById(username).orElseThrow();
    }

    public User updateUser(String username, User newUser) {
        return userRepository.findById(username).map(user -> {
            newUser.setUsername(user.getUsername());
            return userRepository.save(newUser);
        }).orElseThrow();
    }

    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }
}
