package com.nhatduy.parloweb.serviceImpl;

import com.nhatduy.parloweb.dao.UserRepository;
import com.nhatduy.parloweb.entity.User;
import com.nhatduy.parloweb.exception.UserNotFoundException;
import com.nhatduy.parloweb.service.ProtectService;
import com.nhatduy.parloweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return userRepository.findAll();
    }

    @Override
    public User findbyId(int id) {
        Optional<User> result = userRepository.findById(id);
        User user = null;
        if (result.isPresent()){
            user = result.get();
        }
        else {
            throw new UserNotFoundException("ERROR FIND USER ID-"+id);
        }
        return user;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void deletebyId(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }
        List<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),user.getPassword(),user.isActive(),
                true,true,true,authorities);
    }
}
