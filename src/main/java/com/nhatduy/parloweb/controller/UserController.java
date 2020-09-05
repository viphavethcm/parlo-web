package com.nhatduy.parloweb.controller;

import com.nhatduy.parloweb.constants.SystemConstants;
import com.nhatduy.parloweb.dto.UserDTO;
import com.nhatduy.parloweb.entity.StatusResponse;
import com.nhatduy.parloweb.entity.User;
import com.nhatduy.parloweb.exception.BadRequestException;
import com.nhatduy.parloweb.exception.UserNotFoundException;
import com.nhatduy.parloweb.service.UserService;
import com.nhatduy.parloweb.utils.SystemUtils;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v2")
@Api(description = "User Resources")
public class UserController {

    @Autowired
    private UserService userService;

    // find All
    @GetMapping("/users")
    @ApiOperation(value = "Get List of User",
                    response = User.class)
    public ResponseEntity<?> getList(){
        return new ResponseEntity<>(userService.findAll(),HttpStatus.OK);
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
            throw new UserNotFoundException(SystemConstants.MESSAGE_USER_NOT_FOUND+userID);
        }
        return new ResponseEntity<>(userDTO,HttpStatus.OK);
    }
    // add User
    @PostMapping("/users")
    @ApiOperation(value = "Add new User")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "OK"),
            @ApiResponse(code = 400,message = "Something went wrong")
    })
    public ResponseEntity<?> addUser(@ApiParam(value = "Username & Password is required",required = true)@RequestBody UserDTO userDTO){
        if (SystemUtils.PATTERN_ADD_USER(userDTO.getUserName(),userDTO.getPassword())){
            userDTO.setUserID(0);
            userService.save(userDTO);
            return new ResponseEntity<>(
                    new StatusResponse(SystemConstants.MESSAGE_200,System.currentTimeMillis()),HttpStatus.OK);
        }
        else {
            throw new BadRequestException(SystemConstants.MESSAGE_400);
        }
    }

    // update User
    @PutMapping("/users")
    @ApiOperation(value = "Update User")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO){
        if (SystemUtils.PATTERN_ADD_USER(userDTO.getUserName(),userDTO.getPassword())){
            userService.save(userDTO);
            return new ResponseEntity<>(
                    new StatusResponse(SystemConstants.MESSAGE_200,System.currentTimeMillis()),HttpStatus.OK);
        }
        else
            throw new BadRequestException(SystemConstants.MESSAGE_400);
    }

    // delete User
    @DeleteMapping("/users/{userID}")
    @ApiOperation(value = "Delete User by ID")
    public ResponseEntity<?> deleteEmployee(@ApiParam(value = "UserID is required",required = true)@PathVariable int userID){
        UserDTO userDTO = userService.findbyId(userID);
        if (userDTO == null){
            throw new UserNotFoundException(SystemConstants.MESSAGE_USER_NOT_FOUND+userID);
        }
        userService.deletebyId(userID);
        return new ResponseEntity<>(
                new StatusResponse(SystemConstants.MESSAGE_200,System.currentTimeMillis()),HttpStatus.OK);
    }
}