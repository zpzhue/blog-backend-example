package cn.irisz.irisz_blog_backend.controller.result;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * AccountInfoResult类
 * </p>
 *
 * @author 朱志鹏
 * @since 2021/9/5 0005
 */

@Data
public class AccountInfoResult {

    @ApiModelProperty(value = "用户ID")
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "前端显示名字")
    private String displayName;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "是否管理员")
    @TableField("is_admin")
    private Boolean admin;

    @ApiModelProperty(value = "头像地址")
    private String avatar;

    @ApiModelProperty(value = "上次登录时间")
    private LocalDateTime lastLogin;
}
