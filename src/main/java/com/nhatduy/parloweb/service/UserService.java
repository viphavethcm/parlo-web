package com.nhatduy.parloweb.service;

import com.nhatduy.parloweb.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> findAll();

    User findbyId(int id);

    void save(User user);

    void deletebyId(int id);
}
