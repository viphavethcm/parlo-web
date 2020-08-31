package com.nhatduy.parloweb.constants;

public class SystemConstants {
    public static final String MESSAGE_502 = "SOMETHING WENT WRONG";
    public static final String MESSAGE_200 = "OK";
    private static final String TOKEN_SECRET_KEY = "parlokey";

    public static String getTokenSecretKey() {
        return TOKEN_SECRET_KEY;
    }

}
