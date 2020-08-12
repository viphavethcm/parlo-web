package com.nhatduy.parloweb.controller;

import com.nhatduy.parloweb.entity.AuthRequest;
import com.nhatduy.parloweb.entity.AuthResponse;
import com.nhatduy.parloweb.service.UserService;
import com.nhatduy.parloweb.utils.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(description = "User login,Authorization,Authentication")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/authenticate")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "OK"),
            @ApiResponse(code = 502,message = "Invalid Username or Password")
    })
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception{
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(),authRequest.getPassword())
            );
        }catch (BadCredentialsException e){
            throw new Exception("Invalid Username or Password",e);
        }
        final UserDetails userDetails = userService.loadUserByUsername(authRequest.getUserName());
        final String token =jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(token));
    }

}
