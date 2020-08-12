package com.nhatduy.parloweb.controller;

import com.nhatduy.parloweb.entity.User;
import com.nhatduy.parloweb.exception.UserNotFoundException;
import com.nhatduy.parloweb.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Api(description = "User Resources")
public class UserController {

    @Autowired
    private UserService userService;

    // find All
    @GetMapping("/users")
    @ApiOperation(value = "Get List of User",
                    response = User.class)
    public List<User> getList(){
        return userService.findAll();
    }

    // get User by ID
    @GetMapping("/users/{userID}")
    @ApiOperation(value = "Get User Infomation by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "OK"),
            @ApiResponse(code = 500,message = "Something went wrong")
    })
    public User getUserbyID(@ApiParam(value = "UserID is required",required = true)@PathVariable int userID){
        User user = userService.findbyId(userID);
        if (user == null){
            throw new RuntimeException("User not found -"+userID);
        }
        return user;
    }
    // add User
    @PostMapping("/users")
    @ApiOperation(value = "Add new User")
    public User addUser(@ApiParam(value = "Username & Password is required",required = true)@RequestBody User user){
        user.setUserID(0);
        userService.save(user);
        return user;
    }

    // update User
    @PutMapping("/users")
    @ApiOperation(value = "Update User")
    public User updateUser(@RequestBody User user){
        userService.save(user);
        return user;
    }

    // delete User
    @DeleteMapping("/users/{userID}")
    @ApiOperation(value = "Delete User by ID")
    public String deleteEmployee(@ApiParam(value = "UserID is required",required = true)@PathVariable int userID){
        User user = userService.findbyId(userID);
        if (user == null){
            throw new UserNotFoundException("User not found-"+userID);
        }
        userService.deletebyId(userID);
        return "Delete Sucessfull User ID-"+userID;
    }

}