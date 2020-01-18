package com.fsl.demo.util;


/**
 * @description : 响应状态码
 * @author      : Hanqing.Chen
 * @date        : 2019/11/5 14:07
 * @version     : V1.0.0
 * Copyright(C)易比得信息服务(北京)有限公司-版权所有
 */
public enum ResponseCode {


    NOT_FOUND(404,"请求路径不存在"),
    SERVER_ERROR(500,"服务器内部错误"),
    GATEWAY_TIMEOUT(504,"网关超时"),

    /**
     * 成功
     */
    SUCCESS(0, "请求成功"),

    /**
     * 失败
     */
    FAILED(1, "请求失败"),

    UNAUTHENTICATED(10001,"此操作需要登陆系统！"),
    UNAUTHORISE(10002,"权限不足，无权操作！"),
    INVALID_PARAM(10003,"非法参数！"),

    AUTH_USERNAME_NONE(23001,"请输入账号！"),
    AUTH_PASSWORD_NONE(23002,"请输入密码！"),
    AUTH_USERTYPE_NONE(23003,"用户类型不正确！"),
    AUTH_ACCOUNT_NOTEXISTS(23004,"账号不存在！"),
    AUTH_CREDENTIAL_ERROR(23005,"账号或密码错误！"),
    AUTH_LOGIN_APPLYTOKEN_FAIL(23006,"申请令牌失败！"),
    AUTH_LOGIN_TOKEN_SAVEFAIL(23007,"存储令牌失败！"),
    AUTH_TOKEN_IS_NOT_EXIST(23008,"用户令牌不存在或已过期"),



    SYS_ADMIN_IS_NOT_DELETE(101, "系统管理员不能删除"),
    CUR_USER_IS_NOT_DELETE(102, "当前用户不能删除"),
    FIRST_DELETE_SUB_MENU_OR_BUTTON(103, "请先删除子菜单或按钮"),
    USERNAME_IS_EXIST(104, "用户名已存在"),
    ROLECODE_IS_EXIST(105, "角色编码已存在"),
    ROLENAME_IS_EXIST( 106,"角色名称已存在"),
    COMPANY_NAME_IS_EXIST( 107,"顾问名称已存在"),
    DICTIONARY_IS_EXIST( 108,"数据名称已存在"),
    USERID_IS_NOT_NULL( 109,"用户不能为空"),
    DEPTID_AND_USERID_IS_NOT_NULL( 110,"部门ID和用户ID不能为空"),
    USERID_AND_USERTYPE_IS_NOT_NULL( 111,"用户ID和用户类型不能为空"),
    ROLE_AND_USER_IS_NOT_NULL( 112,"角色和用户不能为空"),
    ROLE_CODE_IS_NOT_NULL( 113,"角色编号不能为空"),
    COMPANY_DRAFT_IS_EXIST( 114,"顾问修改草稿已存在，请先删除或从我的顾问列表选择编辑"),
    ROLE_REGION_IS_NOT_NULL( 115,"角色所在区域不能为空"),
    PROJECT_ABBREVIATION_IS_NOT_NULL( 116,"项目简称不能为空"),
    LONG_URL_IS_NOT_NULL( 117,"长网址不能为空"),
    SHORT_URL_BUILD_FAILED( 118,"短网址生成失败"),
    STORE_KEY_IS_NOT_NULL( 119,"store_key不能为空"),
    WEIXIN_BIND_SUCCESS(120, "微信绑定成功"),
    WEIXIN_LOGIN_FAILED(121, "微信登录失败"),
    USERNAME_IS_ALREADY_BIND_WECHAT(122, "此用户已经绑定微信"),
    USERNAME_IS_NOT_BIND_WECHAT(123, "当前用户未绑定微信,不需要解除绑定"),
    USERNAME_IS_NOT_MATCH(124, "要解绑的用户不是当前用户"),
    CONFIRM_TYPE_IS_NOT_NULL(125, "确认类型不能为空"),
    CONFIRM_MSG_IS_NOT_NULL(126, "确认消息不能为空"),
    MISSING_PARAM(127,"缺少参数"),
    SHORT_URL_IS_NOT_NULL( 128,"短网址不能为空"),
    UN_BIND_FAILED(129, "参数不存在");



    public  static  ResponseCode findCommonCode( int code) {


        for (ResponseCode responseCode : values()) {
            if (code == responseCode.code) {
                return responseCode;
            }
        }
        return null;
    }




    ResponseCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;

    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

