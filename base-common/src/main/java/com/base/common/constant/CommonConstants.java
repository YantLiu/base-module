package com.base.common.constant;

/**
 * Created by ace on 2017/8/29.
 */
public class CommonConstants {
    private CommonConstants() {
    }

    public static final String RESOURCE_TYPE_MENU = "menu";
    public static final String RESOURCE_TYPE_BTN = "button";
    // 用户token异常
    public static final Integer EX_USER_INVALID_CODE = 40101;
    public static final Integer EX_USER_PASS_INVALID_CODE = 40001;
    // 客户端token异常
    public static final Integer EX_CLIENT_INVALID_CODE = 40301;
    public static final Integer EX_CLIENT_FORBIDDEN_CODE = 40331;
    public static final Integer EX_OTHER_CODE = 500;
    public static final String CONTEXT_KEY_USER_ID = "currentUserId";
    public static final String CONTEXT_KEY_USERNAME = "currentUserName";
    public static final String CONTEXT_KEY_USER_NAME = "currentUser";
    public static final String CONTEXT_KEY_USER_TOKEN = "currentUserToken";

    public static final String JWT_KEY_ID = "id";
    public static final String JWT_KEY_NAME = "name";
    public static final String JWT_KEY_JSON_STR = "jsonStr";

    public static final String VALID_STATUS = "validStatus";
}
