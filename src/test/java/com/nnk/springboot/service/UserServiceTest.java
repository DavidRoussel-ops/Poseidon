package com.nnk.springboot.service;

import com.nnk.springboot.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Test
    public void testGetUsers() throws Exception {
        Iterable<User> users = userService.getUsers();
        assertThat(users).isNotNull();
    }

    @Test
    public void testGetUserById() throws Exception {
        User user = new User();
        user.setFullname("Test");
        user.setUsername("Test");
        user.setPassword(encoder.encode("Test123!"));
        user.setRole("ADMIN");
        userService.addUser(user);
        userService.getUserById(1);
        int lastId = 0;
        Iterable<User> allUsers = userService.getUsers();
        for (User user1 : allUsers) {
            lastId = user1.getId();
        }
        assertThat(user).isNotNull();
        userService.deleteUserById(lastId);
    }

    @Test
    public void testGetUserByUsername() throws Exception {
        User user = new User();
        user.setFullname("Test");
        user.setUsername("Test");
        user.setPassword(encoder.encode("Test123!"));
        user.setRole("ADMIN");
        userService.addUser(user);
        userService.getUserByUsername("Test");
        int lastId = 0;
        Iterable<User> allUsers = userService.getUsers();
        for (User user1 : allUsers) {
            lastId = user1.getId();
        }
        assertThat(user).isNotNull();
        userService.deleteUserById(lastId);
    }

    @Test
    public void testAddUser() throws Exception {
        User user = new User();
        user.setFullname("Test");
        user.setUsername("Test");
        user.setPassword(encoder.encode("Test123!"));
        user.setRole("ADMIN");
        userService.addUser(user);
        int lastId = 0;
        Iterable<User> allUsers = userService.getUsers();
        for (User user1 : allUsers) {
            lastId = user1.getId();
        }
        assertThat(user).isNotNull();
        userService.deleteUserById(lastId);
    }

    @Test
    public void testDeleteUserById() throws Exception {
        User user = new User();
        user.setFullname("Test");
        user.setUsername("Test");
        user.setPassword(encoder.encode("Test123!"));
        user.setRole("ADMIN");
        userService.addUser(user);
        Iterable<User> allUsers = userService.getUsers();
        int counter = 0;
        int lastId = 0;
        for (User user1 : allUsers) {
            counter ++;
            lastId = user1.getId();
        }
        userService.deleteUserById(lastId);
        counter --;
        Assertions.assertEquals(0, counter);
    }

}
