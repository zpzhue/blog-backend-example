package cn.irisz.irisz_blog_backend.service.impl;

import cn.irisz.irisz_blog_backend.entity.BlogCategory;
import cn.irisz.irisz_blog_backend.mapper.BlogCategoryMapper;
import cn.irisz.irisz_blog_backend.service.BlogCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客分类表 服务实现类
 * </p>
 *
 * @author 朱志鹏
 * @since 2021-08-22
 */
@Service
public class BlogCategoryServiceImpl extends ServiceImpl<BlogCategoryMapper, BlogCategory> implements BlogCategoryService {

}
