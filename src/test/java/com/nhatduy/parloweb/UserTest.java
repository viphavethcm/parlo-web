package com.nhatduy.parloweb;

import com.nhatduy.parloweb.dao.UserRepository;
import com.nhatduy.parloweb.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void addUser(){
        User user = new User();
        user.setUserID(0);
        user.setUserName("test1");
        user.setPassword("456");
        userRepository.save(user);

    }

}
