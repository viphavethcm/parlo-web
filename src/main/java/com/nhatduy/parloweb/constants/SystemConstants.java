package com.nhatduy.parloweb.constants;

public class SystemConstants {
    public static final String MESSAGE_400 = "SOMETHING WENT WRONG";
    public static final String MESSAGE_200 = "OK";
    public static final String MESSAGE_USER_NOT_FOUND = "User Not Found: ";
    private static final String TOKEN_SECRET_KEY = "parlokey";

    public static String getTokenSecretKey() {
        return TOKEN_SECRET_KEY;
    }

}
