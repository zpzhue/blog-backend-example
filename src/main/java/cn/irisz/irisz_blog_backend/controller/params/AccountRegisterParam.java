package cn.irisz.irisz_blog_backend.controller.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * AccountLogin类
 * </p>
 *
 * @author 朱志鹏
 * @since 2021/9/5 0005
 */

@ApiModel("用户注册参数")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = false)
public class AccountRegisterParam {
    private static final long serialVersionUID = 1L;

    @NotEmpty
    @ApiModelProperty(value = "用户名")
    private String username;

    @NotEmpty
    @ApiModelProperty(value = "密码")
    private String password;

    @NotEmpty
    @ApiModelProperty(value = "前端显示名字")
    private String displayName;

    @NotEmpty
    @Email
    @ApiModelProperty(value = "邮箱")
    private String email;
}
