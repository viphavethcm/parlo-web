package com.nhatduy.parloweb.serviceImpl;

import com.nhatduy.parloweb.dao.UserRepository;
import com.nhatduy.parloweb.dto.RoleDTO;
import com.nhatduy.parloweb.dto.UserDTO;
import com.nhatduy.parloweb.entity.Role;
import com.nhatduy.parloweb.entity.User;
import com.nhatduy.parloweb.exception.UserNotFoundException;
import com.nhatduy.parloweb.service.UserService;
import com.nhatduy.parloweb.utils.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public List<UserDTO> findAll() {
        List<UserDTO> listDTO = new ArrayList<>();
        for (User user:userRepository.findAll()){
            listDTO.add(converToDTO(user));
        }
        return listDTO;
    }

    @Override
    public UserDTO findbyId(int id) {
        Optional<User> result = userRepository.findById(id);
        UserDTO userDTO = null;
        if (result.isPresent()){
            User user = result.get();
            userDTO = converToDTO(user);
        }
        else {
            throw new UserNotFoundException("ERROR FIND USER ID-"+id);
        }
        return userDTO;
    }

    @Override
    public void save(UserDTO userDTO) {
        userRepository.save(convertToModel(userDTO));
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
    public String passwordEncoder(String password){
        return new BCryptPasswordEncoder().encode(password);
    }

    public UserDTO converToDTO (User user){
        List<RoleDTO> roles = new ArrayList<>();
        UserDTO userDTO = new UserDTO();
        userDTO.setUserID(user.getUserID());
        userDTO.setUserName(user.getUserName());
        userDTO.setPassword(user.getPassword());
        userDTO.setActive(user.isActive());
        userDTO.setCreated_Date(user.getCreated_Date());
        userDTO.setModified_Date(user.getModified_Date());
        for (Role role :user.getRoles()){
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setRoleID(role.getRoleID());
            roleDTO.setRoleName(role.getRoleName());
            roles.add(roleDTO);
        }
        userDTO.setRoles(roles);
        return userDTO ;
    }

    @Override
    public User convertToModel(UserDTO userDTO) {
        User user = new User();
        if (userDTO.getCreated_Date() == null){
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            userDTO.setCreated_Date(SystemUtils.convertToDate(timestamp));
        }
        user.setUserID(userDTO.getUserID());
        user.setUserName(userDTO.getUserName());
        user.setPassword(passwordEncoder(userDTO.getPassword()));
        user.setActive(true);
        user.setCreated_Date(userDTO.getCreated_Date());
        user.setModified_Date(userDTO.getModified_Date());
        for (RoleDTO roleDTO :userDTO.getRoles()){
            Role role = new Role();
            role.setRoleID(roleDTO.getRoleID());
            role.setRoleName(roleDTO.getRoleName());
            user.addRole(role);
        }
        return user;
    }

}
