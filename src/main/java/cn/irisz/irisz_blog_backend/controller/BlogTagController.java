package cn.irisz.irisz_blog_backend.controller;


import cn.irisz.irisz_blog_backend.common.convent.BlogTagConvent;
import cn.irisz.irisz_blog_backend.common.exception.BusinessException;
import cn.irisz.irisz_blog_backend.common.result.PageResult;
import cn.irisz.irisz_blog_backend.common.result.R;
import cn.irisz.irisz_blog_backend.common.result.ResponseEnum;
import cn.irisz.irisz_blog_backend.controller.params.CreateBlogTagParam;
import cn.irisz.irisz_blog_backend.entity.BlogTag;
import cn.irisz.irisz_blog_backend.service.BlogTagService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 博客标签表 前端控制器
 * </p>
 *
 * @author 朱志鹏
 * @since 2021-08-22
 */
@Api(tags = "标签管理")
@RestController
@RequestMapping("/blog/tag")
public class BlogTagController {
    @Resource
    private BlogTagService blogTagService;

    @ApiOperation("标签列表分页查询")
    @GetMapping("/list")
    public R<PageResult<BlogTag>> articleTagList(@RequestParam(required = false) String tagName,
                                                 @RequestParam(defaultValue = "1") int page,
                                                 @RequestParam(defaultValue = "10") int pageSize){
        LambdaQueryWrapper<BlogTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(tagName != null && !tagName.isEmpty(), BlogTag::getName, tagName);
        Page<BlogTag> pageParam = new Page<>(page, pageSize);
        Page<BlogTag> blogTagPage = blogTagService.page(pageParam, queryWrapper);
        return R.<PageResult<BlogTag>>
                        ok()
                .data(new PageResult<BlogTag>()
                        .setPage(page)
                        .setPageSize(pageSize)
                        .setTotal(blogTagPage.getTotal())
                        .setPageData(blogTagPage.getRecords()));
    }

    @ApiOperation("博客标签创建接口")
    @PostMapping("/")
    public R<Boolean> createBlogTag(@RequestBody @Validated CreateBlogTagParam param){
        BlogTag blogTag = BlogTagConvent.INSTANCE.convent(param);
        boolean b = blogTagService.save(blogTag);
        if(b){
            return R.<Boolean>ok().data(true).message("保存标签成功");
        }else {
            throw new BusinessException(ResponseEnum.SAVE_BLOG_TAG_ERROR);
        }
    }
}
