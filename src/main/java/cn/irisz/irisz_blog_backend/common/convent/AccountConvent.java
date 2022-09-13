package cn.irisz.irisz_blog_backend.common.convent;

import cn.irisz.irisz_blog_backend.controller.params.AccountLoginParam;
import cn.irisz.irisz_blog_backend.controller.params.AccountRegisterParam;
import cn.irisz.irisz_blog_backend.controller.result.SysAccountInfo;
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
public interface AccountConvent {
    AccountConvent INSTANCE = Mappers.getMapper(AccountConvent.class);

    SysAccount convent(AccountRegisterParam param);

    SysAccountInfo convent2SysAccountInfo(SysAccount account);
}
