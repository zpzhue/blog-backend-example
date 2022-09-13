package cn.irisz.irisz_blog_backend.common.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * API 通用返回结果
 * </p>
 *
 * @author 朱志鹏
 * @since 2021-08-22
 */
@ApiModel("通用返回模型对象")
@Data
@Accessors(chain = true)
public class R<T> {

    @ApiModelProperty(value = "返回的状态码", example = "2000")
    private Integer code;

    @ApiModelProperty("服务的返回的消息内容，如果返回状态码异常，可以显示此信息")
    private String message;

    @ApiModelProperty("通用数据对象")
    private T data;

    /**
     * 构造器私有
     */
    private R(){}

    /**
     * 返回成功
     * @return R
     */
    public static <T> R <T> ok(){
        R<T> r = new R<>();
        r.setCode(ResponseEnum.SUCCESS.getCode());
        r.setMessage(ResponseEnum.SUCCESS.getMessage());
        return r;
    }

    /**
     * 返回失败
     * @return R
     */
    public static <T> R <T> error(){
        R<T> r = new R<>();
        r.setCode(ResponseEnum.ERROR.getCode());
        r.setMessage(ResponseEnum.ERROR.getMessage());
        return r;
    }

    /**
     * 设置特定结果
     * @param responseEnum 返回枚举
     * @return R
     */
    public static <T> R <T> setResult(ResponseEnum responseEnum){
        R<T> r = new R<>();
        r.setCode(responseEnum.getCode());
        r.setMessage(responseEnum.getMessage());
        return r;
    }

    /**
     * 设置返回消息
     * @param message 返回给前端的消息
     * @return R
     */
    public R<T> message(String message){
        this.setMessage(message);
        return this;
    }

    /**
     * 设置返回码
     * @param code 返回码
     * @return R
     */
    public R<T> code(Integer code){
        this.setCode(code);
        return this;
    }

    /**
     * 设置返回的结果数据
     * @param data 结果数据
     * @return R
     */
    public R<T> data(T data){
        this.data = data;
        return this;
    }
}