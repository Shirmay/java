package com.zlb.sso.client.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class CommonUtils {

    public static String constructRedirectUrl(String casServerLoginUrl, String serviceParameterName, String serverName){
        String redirectUrl = "";
        try {
            if (casServerLoginUrl.indexOf("?") != -1){
                redirectUrl = casServerLoginUrl + "&" + serviceParameterName + "=" + URLDecoder.decode(serverName, "UTF-8");
            }else{
                redirectUrl = casServerLoginUrl + "?" + serviceParameterName + "=" + URLDecoder.decode(serverName, "UTF-8");
            }
        }catch (UnsupportedEncodingException ex){
            throw new RuntimeException(ex);
        }
        return redirectUrl;
    }

    public static String constructServerValidationUrl(){
        return "";
    }

}
