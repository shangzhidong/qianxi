package com.example.tasela.utils;

/**
 * @author jinmos
 * @date 2019-09-17 14:26
 */
public enum MessageInfo {

    INSERT_TRUE(2002,"添加成功"),
    INSERT_FALSE(2003,"添加失败"),
    UPDATE_TRUE(3002,"更新成功"),
    UPDATE_FALSE(3003,"更新失败"),
    PASS_ERROR(1000,"密码错误"),
    LOGIN_TRUE(2000,"登录成功"),
    TOKEN_INVALID_LOGIN(3000,"token失效，请重新登录"),
    SELECT_OVERTIME(6001,"查询超时，请刷新页面"),
    REGISTER_TRUE(20001,"注册成功"),
    USERNAME_INTURE(2005,"该用户名已经被注册过"),
    SERVER_ERR(5000,"服务器内部错误"),
    PARAMER_NULL(6001,"入参为空"),
    USERNAME_PASS_ERROR(2007,"用户名或密码错误")
    ;

    private Integer code;
    private String message;

    MessageInfo(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
    /**
     * 根据code获取当前的枚举对象
     * @param code
     * @return GenderColumn
     */
    public static MessageInfo of(Integer code) {
        if (code == null) {
            return null;
        }
        for (MessageInfo status : values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return null;
    }

}
