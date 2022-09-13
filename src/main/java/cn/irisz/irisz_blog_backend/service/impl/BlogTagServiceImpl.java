package cn.irisz.irisz_blog_backend.service.impl;

import cn.irisz.irisz_blog_backend.entity.BlogTag;
import cn.irisz.irisz_blog_backend.mapper.BlogTagMapper;
import cn.irisz.irisz_blog_backend.service.BlogTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客标签表 服务实现类
 * </p>
 *
 * @author 朱志鹏
 * @since 2021-08-22
 */
@Service
public class BlogTagServiceImpl extends ServiceImpl<BlogTagMapper, BlogTag> implements BlogTagService {

}
