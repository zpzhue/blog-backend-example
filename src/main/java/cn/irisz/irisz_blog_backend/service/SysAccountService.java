package cn.irisz.irisz_blog_backend.service;

import cn.irisz.irisz_blog_backend.controller.result.AccountLoginResult;
import cn.irisz.irisz_blog_backend.entity.SysAccount;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author 朱志鹏
 * @since 2021-08-22
 */
public interface SysAccountService extends IService<SysAccount> {

    /**
     * 用户名密码登录
     */
    AccountLoginResult loginByUsernameAndPassword(String username, String password);

    /**
     * 通过用户ID获取用户详情
     */
    SysAccount getUserInfoById(Long userId);

    /**
     * 通过用户名获取用户详情
     */
    SysAccount getAccountByUsername(String username);

    /**
     * 注册用户
     */
    boolean registerAccount(SysAccount account);

    /**
     * 注销用户
     */
    boolean logout();
}
