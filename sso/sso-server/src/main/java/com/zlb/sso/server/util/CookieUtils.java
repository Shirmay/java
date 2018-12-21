package com.zlb.sso.server.util;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class CookieUtils {

    public static String getCookie(HttpServletRequest request, String key){
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            if (Objects.equals(cookie.getName(), key)){
                return cookie.getName();
            }
        }
        return null;
    }

    public static void addCookie(HttpServletRequest request, HttpServletResponse response, String key, String ticket){
        Cookie cookie = new Cookie(key, ticket);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 60 * 60 * 24);
        if ("https".equals(request.getScheme())) {
            cookie.setSecure(true);
        }
        response.addCookie(cookie);
    }

    public static void removeCookie(HttpServletResponse response, String key){
        Cookie cookie = new Cookie(key, null);
        cookie.setPath("/");
        cookie.setMaxAge(-1000);
        response.addCookie(cookie);
    }

}
