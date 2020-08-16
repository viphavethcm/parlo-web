package com.nhatduy.parloweb.utils;

import org.springframework.http.HttpHeaders;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class HeaderUtils {

    private static HeaderUtils headerUtils = null;

    public static HeaderUtils getInstance() {
        if (headerUtils == null) {
            headerUtils = new HeaderUtils();
        }
        return headerUtils;
    }
//jádasdádasd

    public Cookie setCookie(HttpServletResponse response,String userName){
        Cookie cookie = new Cookie("userName",userName);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(24*60*60*60);
        response.addCookie(cookie);
        return cookie;
    }

    public HttpHeaders setHeaders(Cookie cookie){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json");
        headers.add("Cookie",cookie.getValue());
        return headers;
    }


}
