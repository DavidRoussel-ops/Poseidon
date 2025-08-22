package com.nnk.springboot.service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    /*@MockBean
    UserRepository userRepository;*/

    @Test
    public void testGetUsers() throws Exception {
        Iterable<User> users = userService.getUsers();
        assertThat(users).isNotNull();
    }

    /*@Test
    public void testGetUserById() throws Exception {
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(mock(User.class)));
        userService.getUserById(anyInt());
    }*/

    /*@Test
    public void testGetUserByUsername() throws Exception {
        User user = new User();
        user.setFullname("Test");
        user.setUsername("TestUsername");
        user.setPassword("Test123!");
        user.setRole("ADMIN");
        userService.addUser(user);
        when(userService.getUserByUsername("TestUsername")).thenReturn(user);
        userService.deleteUserById(user.getId());
    }*/

    @Test
    public void testAddUser() throws Exception {
        User user = new User();
        user.setFullname("Test");
        user.setUsername("Test");
        user.setPassword("Test123!");
        user.setRole("ADMIN");
        userService.addUser(user);
        assertThat(user).isNotNull();
        userService.deleteUserById(user.getId());
    }

    /*@Test
    public void testDeleteUserById() throws Exception {
        User user = new User();
        user.setFullname("Test");
        user.setUsername("TestUsername");
        user.setPassword("Test123!");
        user.setRole("ADMIN");
        userService.addUser(user);
        Iterable<User> allUsers = userService.getUsers();
        int counter = 0;
        for (User user1 : allUsers) {
            counter ++;
        }
        userService.deleteUserById(user.getId());
        Assertions.assertEquals(0, counter);
    }*/

}
