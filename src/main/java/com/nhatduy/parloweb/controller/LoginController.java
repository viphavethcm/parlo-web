package com.nhatduy.parloweb.controller;

import com.nhatduy.parloweb.entity.AuthRequest;
import com.nhatduy.parloweb.entity.AuthResponse;
import com.nhatduy.parloweb.entity.User;
import com.nhatduy.parloweb.entity.UserErrorResponse;
import com.nhatduy.parloweb.service.ProtectService;
import com.nhatduy.parloweb.service.UserService;
import com.nhatduy.parloweb.serviceImpl.ProtectServiceImpl;
import com.nhatduy.parloweb.utils.HeaderUtils;
import com.nhatduy.parloweb.utils.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    public ResponseEntity<?> createAuthenticationToken(HttpSession session, HttpServletResponse response, @RequestBody AuthRequest authRequest) throws Exception {
        ProtectService protectService = new ProtectServiceImpl();
        ResponseEntity responseEntity = null;
        HttpHeaders headers;
        if (protectService.SQL_Injection(authRequest) == false) {
            try {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
                );
            } catch (BadCredentialsException e) {
                throw new Exception("Invalid Username or Password", e);
            }
            final UserDetails userDetails = userService.loadUserByUsername(authRequest.getUserName());

            if (userDetails != null) {
                session.setAttribute("userName", userDetails);
                Cookie cookie = HeaderUtils.getInstance().setCookie(response, authRequest.getUserName());
                response.addCookie(cookie);
                headers = HeaderUtils.getInstance().setHeaders(cookie);
                final String token = jwtUtil.generateToken(userDetails);
                responseEntity =new ResponseEntity<>(new AuthResponse(token), headers, HttpStatus.OK);
            } else {
                headers = HeaderUtils.getInstance().setHeaders(null);
                responseEntity = new ResponseEntity<>(new UserErrorResponse(400, "Invalid Username or Password", System.currentTimeMillis()), headers, HttpStatus.BAD_REQUEST);
            }
        }
        return responseEntity;
    }

}

