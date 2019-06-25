package com.example.querydsl.excption;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Created by IntelliJ IDEA.
 * AbstractUser: gaochen
 * Date: 2018/12/5
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ResponseErrorCode {

    GENERAL_NO_AUTHORITY("0001", "操作者没有权限操作"),
    GENERAL_PARAMS_ERROR("0002", "参数错误"),
    GENERAL_PARAMS_NOT_EXIST("0003", "对象未找到"),
    GENERAL_UNKNOWN_AUTHORITY("0099", "未知错误"),

    FORGET_PWD_USERNAME_ERROR("0201", "用户名错误", true),
    FORGET_PWD_UUID_ERROR("0202", "UUID错误"),
    FORGET_PWD_PIC_CODE_INVALID_ERROR("0203", "图片验证码失效", true),
    FORGET_PWD_PIC_CODE_WRITE_ERROR("0204", "图片验证码输入错误", true),
    FORGET_PWD_MSM_CODE_ERROR("0205", "短信验证码错误", true),
    FORGET_PWD_MSM_CODE_TIMEOUT("0206", "短信验证码超时"),
    FORGET_PWD_MSM_LIMIT("0207", "发送短信频率过高"),
    FORGET_PWD_PIC_CODE_TIMEOUT("0208", "图片验证码超时"),

    LOGIN_USERNAME_ERROR("0401", "用户名不正确", true),
    LOGIN_PASSWORD_ERROR("0402", "密码不正确", true),
    USER_INVALID("0403", "当前账号不存在"),
    USER_USERNAME_EXIST("0404", "用户名已存在", true),

    ROLE_EXIST_USER_CAN_NOT_DELETE("0501", "有关联的用户不能删除角色"),
    ROLE_NO_OUTER("0502", "权限越界"),
    ROLE_DEFAULT_NOT_EXIST("0503", "默认角色不存在,请联系管理员"),

    FILE_UPLOAD_ERROR("0601", "上传文件异常"),
    FILE_DOWNLOAD_ERROR("0602", "下载文件异常"),

    STDLIB_INDUSTRY_NATIONAL_ECONOMY_CLASSIFICATION("0701", "行标类型下国民经济分类不能为空");

    private String code;

    private String description;

    private boolean isForm;

    ResponseErrorCode(String code, String description, boolean isForm) {
        this.code = code;
        this.description = description;
        this.isForm = isForm;
    }

    ResponseErrorCode(String code, String description) {
        this.code = code;
        this.description = description;
        this.isForm = false;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }

    public boolean isForm() {
        return isForm;
    }
}
