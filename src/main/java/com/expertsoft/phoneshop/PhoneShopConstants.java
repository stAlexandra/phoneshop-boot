package com.expertsoft.phoneshop;

public class PhoneShopConstants {

    public static final String ROOT_PATH = "/";
    public static final String PHONES_PATH = "/phones";
    public static final String ADMIN_PATH = "/admin";
    public static final String LOGIN_PATH = "/login";
    public static final String ERROR_PATH = "/error";

    public static final String FORM_LOGIN_ERROR_PATH = "/login?error=true";
    public static final String FORM_LOGIN_PROCESSING_PATH = "/perform_login";

    private PhoneShopConstants() {
        // instance not allowed
    }
}
