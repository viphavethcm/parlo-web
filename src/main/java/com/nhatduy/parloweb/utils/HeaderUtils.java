package com.nhatduy.parloweb.utils;
import org.springframework.http.HttpHeaders;

public class HeaderUtils {

    private static HeaderUtils headerUtils = null;

    public static HeaderUtils getInstance() {
        if (headerUtils == null) {
            headerUtils = new HeaderUtils();
        }
        return headerUtils;
    }

    public HttpHeaders setHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json");
        return headers;
    }


}
