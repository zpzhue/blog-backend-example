package cn.irisz.irisz_blog_backend.controller.params;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apiguardian.api.API;

import java.time.LocalDateTime;

/**
 * <p>
 * AccountLogin类
 * </p>
 *
 * @author 朱志鹏
 * @since 2021/9/5 0005
 */

@ApiModel("用户登录参数")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = false)
public class AccountLoginParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;
}
