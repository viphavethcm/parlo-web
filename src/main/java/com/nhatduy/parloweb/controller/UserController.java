package com.nhatduy.parloweb.controller;

import com.nhatduy.parloweb.dto.UserDTO;
import com.nhatduy.parloweb.entity.StatusResponse;
import com.nhatduy.parloweb.entity.User;
import com.nhatduy.parloweb.exception.UserNotFoundException;
import com.nhatduy.parloweb.service.UserService;
import com.nhatduy.parloweb.utils.HeaderUtils;
import com.nhatduy.parloweb.utils.SystemUtils;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@Api(description = "User Resources")
public class UserController {

    @Autowired
    private UserService userService;

    // find All
    @GetMapping("/users")
    @ApiOperation(value = "Get List of User",
                    response = User.class)
    public List<UserDTO> getList(){
        return userService.findAll();
    }

    // get User by ID
    @GetMapping("/users/{userID}")
    @ApiOperation(value = "Get User Infomation by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "OK"),
    })
    public UserDTO getUserbyID(@ApiParam(value = "UserID is required",required = true)@PathVariable int userID){

        UserDTO userDTO = userService.findbyId(userID);
        if (userDTO == null){
            throw new RuntimeException("User not found -"+userID);
        }
        return userDTO;
    }
    // add User
    @PostMapping("/users")
    @ApiOperation(value = "Add new User")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "OK"),
            @ApiResponse(code = 502,message = "Something went wrong")
    })
    public ResponseEntity<?> addUser(@ApiParam(value = "Username & Password is required",required = true)@RequestBody UserDTO userDTO){
        ResponseEntity responseEntity= null;
        if (SystemUtils.checkPattern(userDTO.getUserName(),userDTO.getPassword())){
            userDTO.setUserID(0);
            userService.save(userDTO);
            responseEntity = new ResponseEntity<>(
                            new StatusResponse(200, "Successfull",
                            System.currentTimeMillis()), HeaderUtils.getInstance().setHeaders(),
                            HttpStatus.OK);
        }
        else {
            responseEntity = new ResponseEntity<>(
                            new StatusResponse(502, "Something went wrong",
                            System.currentTimeMillis()), null,
                            HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }

    // update User
    @PutMapping("/users")
    @ApiOperation(value = "Update User")
    public UserDTO updateUser(@RequestBody UserDTO userDTO){
        if (SystemUtils.checkPattern(userDTO.getUserName(),userDTO.getPassword())){
            userService.save(userDTO);
        }
        return userDTO;
    }

    // delete User
    @DeleteMapping("/users/{userID}")
    @ApiOperation(value = "Delete User by ID")
    public String deleteEmployee(@ApiParam(value = "UserID is required",required = true)@PathVariable int userID){
        UserDTO userDTO = userService.findbyId(userID);
        if (userDTO == null){
            throw new UserNotFoundException("User not found-"+userID);
        }
        userService.deletebyId(userID);
        return "Delete Sucessfull User ID-"+userID;
    }

}