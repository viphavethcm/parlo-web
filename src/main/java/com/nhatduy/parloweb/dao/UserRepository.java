package com.nhatduy.parloweb.dao;

import com.nhatduy.parloweb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("select user from User user join fetch user.roles where user.userName =:userName and user.active = true")
    User findByUserName(@Param("userName") String userName);
}
