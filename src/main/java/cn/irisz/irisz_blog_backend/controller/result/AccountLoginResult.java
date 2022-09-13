package cn.irisz.irisz_blog_backend.controller.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * AccountLoginResult类
 * </p>
 *
 * @author 朱志鹏
 * @since 2021/9/5 0005
 */

@Data
public class AccountLoginResult {

    @ApiModelProperty(value = "用户ID")
    private Long id;

    @ApiModelProperty(value = "生成的JWT token")
    private String token;

    @ApiModelProperty(value = "过期时间")
    private Long expires;

    @ApiModelProperty(value = "用户角色")
    private ArrayList<Object> role;
}
