package com.passportbackend.user;

import com.passportbackend.role.RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UserServiceTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void testFindAllUsers() {
        List<User> users = userService.findAllUsers();
        User user = users.getFirst();
        assertEquals(1, users.size());
        assertEquals("admin", user.getUsername());
        assertNotNull(user.getRole());
    }

    @Test
    void testSaveUser() {
        User admin = new User();
        admin.setUsername("admin");

        User expected = new User();
        expected.setUsername("user");
        expected.setPassword(passwordEncoder.encode("user"));
        expected.setRole(roleService.findAllRoles().getFirst());
        userService.saveUser(expected);
        User actual = userRepository.findById(expected.getUsername()).orElseThrow();
        assertEquals(expected.getUsername(), actual.getUsername());
        assertEquals(expected.getPassword(), actual.getPassword());
        assertEquals(expected.getRole(), actual.getRole());
        assertEquals(expected.isAccountNonExpired(), actual.isAccountNonExpired());
        assertEquals(expected.isAccountNonLocked(), actual.isAccountNonLocked());
        assertEquals(expected.isCredentialsNonExpired(), actual.isCredentialsNonExpired());
        assertEquals(expected.isEnabled(), actual.isEnabled());
    }

    @Test
    void testFindUser() {
        User expected = new User();
        expected.setUsername("user");
        expected.setPassword(passwordEncoder.encode("user"));
        expected.setRole(roleService.findAllRoles().getFirst());
        userRepository.save(expected);
        User actual = userService.findUser(expected.getUsername());
        assertEquals(expected.getUsername(), actual.getUsername());
        assertEquals(expected.getPassword(), actual.getPassword());
        assertEquals(expected.getRole(), actual.getRole());
        assertEquals(expected.isAccountNonExpired(), actual.isAccountNonExpired());
        assertEquals(expected.isAccountNonLocked(), actual.isAccountNonLocked());
        assertEquals(expected.isCredentialsNonExpired(), actual.isCredentialsNonExpired());
        assertEquals(expected.isEnabled(), actual.isEnabled());
    }

    @Test
    void testUpdateUser() {
        User expected = new User();
        expected.setUsername("user");
        expected.setPassword(passwordEncoder.encode("user"));
        expected.setRole(roleService.findAllRoles().getFirst());
        userRepository.save(expected);
        expected.setEnabled(false);
        User actual = userService.updateUser(expected.getUsername(), expected);
        assertEquals(expected.getUsername(), actual.getUsername());
        assertEquals(expected.getPassword(), actual.getPassword());
        assertEquals(expected.getRole(), actual.getRole());
        assertEquals(expected.isAccountNonExpired(), actual.isAccountNonExpired());
        assertEquals(expected.isAccountNonLocked(), actual.isAccountNonLocked());
        assertEquals(expected.isCredentialsNonExpired(), actual.isCredentialsNonExpired());
        assertEquals(expected.isEnabled(), actual.isEnabled());
    }

    @Test
    void testDeleteUser() {
        User expected = new User();
        expected.setUsername("user");
        expected.setPassword(passwordEncoder.encode("user"));
        expected.setRole(roleService.findAllRoles().getFirst());
        userRepository.save(expected);
        long before = userRepository.findById(expected.getUsername()).stream().count();
        userService.deleteUser(expected.getUsername());
        long after = userRepository.findById(expected.getUsername()).stream().count();
        assertTrue(before > after);
    }
}
