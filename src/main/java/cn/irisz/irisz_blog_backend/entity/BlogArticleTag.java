package cn.irisz.irisz_blog_backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文章标签关联表
 * </p>
 *
 * @author 朱志鹏
 * @since 2021-08-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="文章标签关联表")
public class BlogArticleTag implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文章管理标签ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "文章ID")
    private Integer articleId;

    @ApiModelProperty(value = "标签ID")
    private Integer tagId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "是否逻辑删除")
    @TableField("is_delete")
    @TableLogic
    private Boolean deleted;


}
