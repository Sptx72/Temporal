package com.example.temporal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AppTextUtils {

    public static String autoFillNull(String text) {
        return text == null ? "" : text;
    }

    public static boolean isEmpty(String string) {
        return string == null || string.equals("");
    }

    public static boolean anyEmpty(String ... params) {
        for (String text : params) {
            if (isEmpty(text)) {
                return true;
            }
        }

        return false;
    }

    public static boolean compare(String one, String two) {
        return one.equals(two);
    }

    public static boolean isAnEmail(String email) {
        Pattern pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

        Matcher matcher = pattern.matcher(email);

        return matcher.find();
    }
}
