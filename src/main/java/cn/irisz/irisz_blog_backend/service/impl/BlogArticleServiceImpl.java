package cn.irisz.irisz_blog_backend.service.impl;

import cn.irisz.irisz_blog_backend.entity.BlogArticle;
import cn.irisz.irisz_blog_backend.mapper.BlogArticleMapper;
import cn.irisz.irisz_blog_backend.service.BlogArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客文章表 服务实现类
 * </p>
 *
 * @author 朱志鹏
 * @since 2021-08-22
 */
@Service
public class BlogArticleServiceImpl extends ServiceImpl<BlogArticleMapper, BlogArticle> implements BlogArticleService {

}
