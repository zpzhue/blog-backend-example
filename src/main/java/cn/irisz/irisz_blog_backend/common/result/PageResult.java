package cn.irisz.irisz_blog_backend.common.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 通用分页结果类
 * </p>
 *
 * @author 朱志鹏
 * @since 2022/5/1 0001
 */
@Data
@Accessors(chain = true)
public class PageResult<T> {
    @ApiModelProperty(value = "当前页")
    private Integer page;

    @ApiModelProperty(value = "每页条数")
    private Integer pageSize;

    @ApiModelProperty(value = "总数量")
    private Long total;

    @ApiModelProperty(value = "分页数据")
    private List<T> pageData;
}
