package cn.irisz.irisz_blog_backend.controller.result;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * <p>
 * 返回的用户信息结果
 * </p>
 *
 * @author 朱志鹏
 * @since 2021-11-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="用户信息")
public class SysAccountInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    @TableId(value = "id", type = IdType.AUTO)
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

    @ApiModelProperty(value = "用户是否激活")
    @TableField("is_active")
    private Boolean active;

    @ApiModelProperty(value = "用户加入时间")
    private LocalDateTime dateJoined;

    @ApiModelProperty(value = "头像地址")
    private String avatar;

    @ApiModelProperty(value = "上次登录时间")
    private LocalDateTime lastLogin;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
}
