package com.nhatduy.parloweb.dao;

import com.nhatduy.parloweb.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> ,UserDao{

    @EntityGraph("User.roles")
    List<User> findAll();
}
