package com.nhatduy.parloweb.service;

import com.nhatduy.parloweb.dto.UserDTO;
import com.nhatduy.parloweb.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserDTO> findAll();

    UserDTO findbyId(int id);

    void save(UserDTO userDTO);

    void deletebyId(int id);

    UserDTO converToDTO(User user);

    User convertToModel(UserDTO userDTO);
}
