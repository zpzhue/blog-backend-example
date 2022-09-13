package cn.irisz.irisz_blog_backend.controller;


import cn.irisz.irisz_blog_backend.common.convent.AccountConvent;
import cn.irisz.irisz_blog_backend.common.result.R;
import cn.irisz.irisz_blog_backend.controller.params.AccountLoginParam;
import cn.irisz.irisz_blog_backend.controller.params.AccountRegisterParam;
import cn.irisz.irisz_blog_backend.controller.result.AccountLoginResult;
import cn.irisz.irisz_blog_backend.controller.result.SysAccountInfo;
import cn.irisz.irisz_blog_backend.entity.SysAccount;
import cn.irisz.irisz_blog_backend.service.SysAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author 朱志鹏
 * @since 2021-08-22
 */
@Api(tags = "账户管理")
@RestController
@RequestMapping("/account")
public class SysAccountController {
    @Resource
    private SysAccountService sysAccountService;

    @ApiOperation("注册用户")
    @PostMapping("/register")
    public R<Object> register(@RequestBody @Validated AccountRegisterParam param){
        SysAccount account = AccountConvent.INSTANCE.convent(param);
        account.setDateJoined(LocalDateTime.now());
        account.setActive(true);
        boolean b = sysAccountService.registerAccount(account);
        if (b) {
            return R.ok().message("注册成功");
        }else{
            return R.error().message("注册失败");
        }
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public R<AccountLoginResult> login (@RequestBody @Validated AccountLoginParam param){
        AccountLoginResult result = sysAccountService.loginByUsernameAndPassword(param.getUsername(), param.getPassword());
        return R.<AccountLoginResult>ok().message("登录成功").data(result);
    }

    @ApiOperation("用户信息")
    @GetMapping("/info/{id}")
    public R<SysAccountInfo> accountInfo(@PathVariable("id") Long userId){
        SysAccount sysAccount = sysAccountService.getUserInfoById(userId);
        SysAccountInfo accountInfo = AccountConvent.INSTANCE.convent2SysAccountInfo(sysAccount);
        return R.<SysAccountInfo>ok().data(accountInfo);
    }

    @ApiOperation("注销用户")
    @PostMapping("/logout")
    public R<Boolean> logout(){
        boolean logout = sysAccountService.logout();
        return R.<Boolean>ok().data(logout);
    }
}
