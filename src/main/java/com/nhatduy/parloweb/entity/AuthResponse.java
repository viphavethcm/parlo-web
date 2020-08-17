package com.nhatduy.parloweb.entity;

public class AuthResponse {
    private final String userName;
    private final String token;

    public AuthResponse(String userName, String token) {
        this.userName = userName;
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public String getToken() {
        return token;
    }
}
