package com.nhatduy.parloweb.controller;

import com.nhatduy.parloweb.entity.AuthRequest;
import com.nhatduy.parloweb.entity.AuthResponse;
import com.nhatduy.parloweb.entity.User;
import com.nhatduy.parloweb.entity.StatusResponse;
import com.nhatduy.parloweb.service.ProtectService;
import com.nhatduy.parloweb.service.UserService;
import com.nhatduy.parloweb.serviceImpl.ProtectServiceImpl;
import com.nhatduy.parloweb.utils.HeaderUtils;
import com.nhatduy.parloweb.utils.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;

@RestController
@Api(description = "User login,Authorization,Authentication")
@SessionAttributes(value = "userName",types = {User.class})
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/authenticate")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 502, message = "Invalid Username or Password")
    })
    public ResponseEntity<?> createAuthenticationToken(HttpSession session, @RequestBody AuthRequest authRequest) throws Exception {
        ProtectService protectService = new ProtectServiceImpl();
        ResponseEntity responseEntity = null;
        if (protectService.SQL_Injection(authRequest) == false) {
            try {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
                );
            } catch (BadCredentialsException e) {
                throw new BadCredentialsException("Invalid Username or Password", e);
            }
            // check UseDetails
            final UserDetails userDetails = userService.loadUserByUsername(authRequest.getUserName());
            // if Not null --> set Token and Cookie through JSESSIONID
            if (userDetails != null) {
                final String token = jwtUtil.generateToken(userDetails);
                responseEntity =new ResponseEntity<>(new AuthResponse(authRequest.getUserName(),token),
                                                        HeaderUtils.getInstance().setHeaders(), HttpStatus.OK);
            }// if null --> return UserError
            else {
                responseEntity = new ResponseEntity<>(
                                new StatusResponse(502, "Invalid Username or Password",
                                System.currentTimeMillis()), null,
                                HttpStatus.BAD_GATEWAY);
            }
        }
        return responseEntity;
    }

}

