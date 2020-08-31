package com.nhatduy.parloweb.controller;

import com.nhatduy.parloweb.constants.SystemConstants;
import com.nhatduy.parloweb.dto.UserDTO;
import com.nhatduy.parloweb.entity.StatusResponse;
import com.nhatduy.parloweb.entity.User;
import com.nhatduy.parloweb.exception.UserNotFoundException;
import com.nhatduy.parloweb.service.UserService;
import com.nhatduy.parloweb.utils.SystemUtils;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v2")
@Api(description = "User Resources")
public class UserController {

    @Autowired
    private UserService userService;

    // find All
    @GetMapping("/admin/users")
    @ApiOperation(value = "Get List of User",
                    response = User.class)
    public ResponseEntity<?> getList(){
        return new ResponseEntity<>(userService.findAll(),null,HttpStatus.OK);
    }

    // get User by ID
    @GetMapping("/users/{userID}")
    @ApiOperation(value = "Get User Infomation by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "OK"),
    })
    public ResponseEntity<?> getUserbyID(@ApiParam(value = "UserID is required",required = true)@PathVariable int userID){
        UserDTO userDTO = userService.findbyId(userID);
        if (userDTO == null){
            throw new RuntimeException("User not found -"+userID);
        }
        return new ResponseEntity<>(userDTO,null,HttpStatus.OK);
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
        if (SystemUtils.PATTERN_ADD_USER(userDTO.getUserName(),userDTO.getPassword())){
            userDTO.setUserID(0);
            userService.save(userDTO);
            responseEntity = new ResponseEntity<>(
                    new StatusResponse(SystemConstants.MESSAGE_200,System.currentTimeMillis()),HttpStatus.OK);
        }
        else {
            responseEntity = new ResponseEntity<>(
                    new StatusResponse(SystemConstants.MESSAGE_502,System.currentTimeMillis()),HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }

    // update User
    @PutMapping("/users")
    @ApiOperation(value = "Update User")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO){
        ResponseEntity responseEntity;
        if (SystemUtils.PATTERN_ADD_USER(userDTO.getUserName(),userDTO.getPassword())){
            userService.save(userDTO);
            responseEntity = new ResponseEntity(
                    new StatusResponse(SystemConstants.MESSAGE_200,System.currentTimeMillis()),HttpStatus.OK);
        }
        else {
            responseEntity = new ResponseEntity<>(
                    new StatusResponse(SystemConstants.MESSAGE_502,System.currentTimeMillis()),HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }

    // delete User
    @DeleteMapping("/admin/users/{userID}")
    @ApiOperation(value = "Delete User by ID")
    public ResponseEntity<?> deleteEmployee(@ApiParam(value = "UserID is required",required = true)@PathVariable int userID){
        UserDTO userDTO = userService.findbyId(userID);
        if (userDTO == null){
            throw new UserNotFoundException("User not found-"+userID);
        }
        userService.deletebyId(userID);
        return new ResponseEntity<>(
                new StatusResponse(SystemConstants.MESSAGE_200,System.currentTimeMillis()),HttpStatus.OK);
    }

}