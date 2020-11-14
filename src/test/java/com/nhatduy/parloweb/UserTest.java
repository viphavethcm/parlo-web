package com.nhatduy.parloweb;

import com.nhatduy.parloweb.dao.CategoriesRepository;
import com.nhatduy.parloweb.dao.UserRepository;
import com.nhatduy.parloweb.entity.Categories;
import com.nhatduy.parloweb.entity.User;
import com.nhatduy.parloweb.service.CategoriesService;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoriesService categoriesService;

    @Autowired
    CategoriesRepository categoriesRepository;

    @Test
    public void addUser(){
        User user = new User();
        user.setUserID(0);
        user.setUserName("test1");
        user.setPassword("456");
        userRepository.save(user);

    }


}
