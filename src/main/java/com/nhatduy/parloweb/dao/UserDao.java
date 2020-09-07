package com.nhatduy.parloweb.dao;

import com.nhatduy.parloweb.entity.User;

public interface UserDao {
    User findByUserName(String username);
}
