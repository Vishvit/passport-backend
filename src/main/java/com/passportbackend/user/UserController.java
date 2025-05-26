package com.passportbackend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = "/users")
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping(value = "/users")
    public User saveUser(@RequestBody User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userService.saveUser(newUser);
    }

    @GetMapping(value = "/users/{username}")
    public User findUser(@PathVariable String username) {
        return userService.findUser(username);
    }

    @PutMapping(value = "/users/{username}")
    public User updateUser(@PathVariable String username, @RequestBody User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userService.updateUser(username, newUser);
    }

    @DeleteMapping(value = "/users/{username}")
    public ResponseEntity<Object> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return ResponseEntity.ok().build();
    }
}
