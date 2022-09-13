package cn.irisz.irisz_blog_backend.common.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * <p>
 * 统一返回结果枚举
 * </p>
 *
 * @author 朱志鹏
 * @since 2021-08-22
 */
@Getter
@AllArgsConstructor
@ToString
public enum ResponseEnum {
    /**
     * 通用成功结果
     */
    SUCCESS(2000, "成功"),
    /**
     * 通用错误结果
     */
    ERROR(5000, "服务器内部错误"),
    PARAMS_VALID_ERROR(4000, "参数校验错误"),

    /**
     * 5001 ~ 6000 后端服务器错误
     */
    BAD_SQL_GRAMMAR_ERROR(5001, "sql语法错误"),
    SERVLET_ERROR(5002, "servlet请求异常"),
    UPLOAD_ERROR(5003, "文件上传错误"),
    EXPORT_DATA_ERROR(5004, "数据导出失败"),

    /**
     * 4001 ~ 5000 参数校验错误
     */
    LOGIN_AUTH_ERROR(4001, "用户未登录，请登录后重试"),
    LOGIN_TOKEN_CHECK_ERROR(4002, "Token解析错误"),
    LOGIN_USERNAME_OR_PASSWORD_ERROR(4003, "用户或密码错误"),
    USER_NOT_EXIST(4004, "用户不存在"),
    LOGIN_USER_NOT_ACTIVE(4005, "用户未激活"),
    AUTHENTICATION_ERROR(4006, "权限验证失败，请联系管理员添加权限"),
    SAVE_BLOG_TAG_ERROR(4007, "保存标签数据失败"),
//    MOBILE_ERROR(-203, "手机号码不正确"),
//    CODE_NULL_ERROR(205, "验证码不能为空"),
//    CODE_ERROR(206, "验证码错误"),
//    MOBILE_EXIST_ERROR(207, "手机号已被注册"),
//    LOGIN_LOCKED_ERROR(210, "用户被锁定"),
//    USER_BIND_IDCARD_EXIST_ERROR(-301, "身份证号码已绑定"),
//    USER_NO_BIND_ERROR(302, "用户未绑定"),
    ;

    /**
     * 响应状态码
     */
    private final Integer code;
    /**
     * 响应信息
     */
    private final String message;
}