package com.nhatduy.parloweb.daoImpl;

import com.nhatduy.parloweb.dao.UserDao;
import com.nhatduy.parloweb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public User findByUserName(String username) {
        Query query = entityManager.createNativeQuery("select * from USER where userName = ?");
        return (User) query.setParameter(1,username).getSingleResult();
    }
}
