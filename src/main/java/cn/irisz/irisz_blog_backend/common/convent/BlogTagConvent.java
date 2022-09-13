package cn.irisz.irisz_blog_backend.common.convent;

import cn.irisz.irisz_blog_backend.controller.params.AccountRegisterParam;
import cn.irisz.irisz_blog_backend.controller.params.CreateBlogTagParam;
import cn.irisz.irisz_blog_backend.controller.result.SysAccountInfo;
import cn.irisz.irisz_blog_backend.entity.BlogTag;
import cn.irisz.irisz_blog_backend.entity.SysAccount;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * AccountConvent类
 * </p>
 *
 * @author 朱志鹏
 * @since 2021/9/12 0012
 */

@Mapper
public interface BlogTagConvent {
    BlogTagConvent INSTANCE = Mappers.getMapper(BlogTagConvent.class);

    BlogTag convent(CreateBlogTagParam param);
}
