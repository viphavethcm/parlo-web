package com.nhatduy.parloweb.utils;

import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HeaderUtils {

    private static HeaderUtils headerUtils = null;

    public static HeaderUtils getInstance() {
        if (headerUtils == null) {
            headerUtils = new HeaderUtils();
        }
        return headerUtils;
    }

//    public Cookie setCookie(String sessionID){
//        Cookie cookie = new Cookie("JSESSIONID",sessionID);
//        cookie.setPath("/");
//        cookie.setMaxAge(24*60*60*10);
//        cookie.setHttpOnly(true);
//        cookie.setSecure(true);
//        return cookie;
//    }

    public HttpHeaders setHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json");
        return headers;
    }


}
