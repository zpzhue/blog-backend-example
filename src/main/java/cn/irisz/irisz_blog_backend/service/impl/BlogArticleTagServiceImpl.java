package cn.irisz.irisz_blog_backend.service.impl;

import cn.irisz.irisz_blog_backend.entity.BlogArticleTag;
import cn.irisz.irisz_blog_backend.mapper.BlogArticleTagMapper;
import cn.irisz.irisz_blog_backend.service.BlogArticleTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章标签关联表 服务实现类
 * </p>
 *
 * @author 朱志鹏
 * @since 2021-08-22
 */
@Service
public class BlogArticleTagServiceImpl extends ServiceImpl<BlogArticleTagMapper, BlogArticleTag> implements BlogArticleTagService {

}
