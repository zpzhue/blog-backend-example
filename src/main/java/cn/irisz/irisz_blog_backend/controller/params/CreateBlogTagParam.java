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

@ApiModel("博客标签创建接口")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = false)
public class CreateBlogTagParam {
    private static final long serialVersionUID = 1L;

    @NotEmpty
    @ApiModelProperty(value = "标签英文名称")
    private String name;

    @NotEmpty
    @ApiModelProperty(value = "标签中文名称")
    private String displayName;

    @NotEmpty
    @ApiModelProperty(value = "文章标签颜色")
    private String color;
}
