package com.nhatduy.parloweb.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class SystemUtils {

    public static String convertToDate(Timestamp timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = new Date(timestamp.getTime());
        return dateFormat.format(date);
    }

    public static boolean PATTERN_ADD_USER(String userName,String password){
        String PATTERN_USERNAME = "^[a-z0-9]{10,30}+$";
        String PATTERN_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        Pattern checkUsername = Pattern.compile(PATTERN_USERNAME,Pattern.CASE_INSENSITIVE);
        Pattern checkPassword = Pattern.compile(PATTERN_PASSWORD,Pattern.CASE_INSENSITIVE);
        if (checkUsername.matcher(userName).matches() && checkPassword.matcher(password).matches()){
            return true;
        }else
            return false;
    }

    public static boolean PATTERN_SPECIAL_CHARACTER_NUMBERS(String data){
        String PATTERN = "^([^0-9`~!@#$%^&*()_+={}\\[\\]|\\\\:;“’<,>.?๐฿])*$";
        Pattern check = Pattern.compile(PATTERN,Pattern.UNICODE_CHARACTER_CLASS);
        if (check.matcher(data).matches()){
            return true;
        }else
            return false;
    }


}
