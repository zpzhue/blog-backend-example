package cn.irisz.irisz_blog_backend.service.impl;

import cn.irisz.irisz_blog_backend.common.exception.BusinessException;
import cn.irisz.irisz_blog_backend.common.result.ResponseEnum;
import cn.irisz.irisz_blog_backend.controller.result.AccountLoginResult;
import cn.irisz.irisz_blog_backend.entity.SysAccount;
import cn.irisz.irisz_blog_backend.mapper.SysAccountMapper;
import cn.irisz.irisz_blog_backend.service.SysAccountService;
import cn.irisz.irisz_blog_backend.utils.JwtUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author 朱志鹏
 * @since 2021-08-22
 */
@Service
public class SysAccountServiceImpl extends ServiceImpl<SysAccountMapper, SysAccount> implements SysAccountService {

    @Resource
    private SysAccountMapper sysAccountMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    /**
     * 校验用户名和密码，并返回token
     */
    @Override
    public AccountLoginResult loginByUsernameAndPassword(String username, String password) {
        SysAccount sysAccount = sysAccountMapper.selectOne(new LambdaQueryWrapper<SysAccount>().eq(SysAccount::getUsername, username));

        if (sysAccount == null){
            // 用户不存在
            throw new BusinessException((ResponseEnum.USER_NOT_EXIST));
        }
        if (!passwordEncoder.matches(password, sysAccount.getPassword())){
            // 用户名或密码错误
            throw new BusinessException(ResponseEnum.LOGIN_USERNAME_OR_PASSWORD_ERROR);
        }
        if (!sysAccount.isEnabled()){
            // 用户未激活
            throw new BusinessException(ResponseEnum.LOGIN_USER_NOT_ACTIVE);
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                sysAccount, null, sysAccount.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        String token = JwtUtils.createToken(sysAccount.getId(), sysAccount.getUsername());

        AccountLoginResult result = new AccountLoginResult();
        result.setId(sysAccount.getId());
        result.setToken(token);
        result.setExpires(JwtUtils.getTokenExpiresTime());

        // 构建前端所需要的角色参数
        HashMap<String, String> roleMap = new HashMap<>();
        if (sysAccount.getAdmin()){
            roleMap.put("roleName", "Super Admin");
            roleMap.put("value", "super");
        }else{
            roleMap.put("roleName", "Tester");
            roleMap.put("value", "test");
        }
        ArrayList<Object> roles = new ArrayList<>();
        roles.add(roleMap);
        result.setRole(roles);
        return result;
    }

    /**
     * 通过用户ID获取用户详细信息
     */
    @Override
    public SysAccount getUserInfoById(Long userId) {
        return sysAccountMapper.selectOne(new LambdaQueryWrapper<SysAccount>()
                .eq(SysAccount::getId, userId).eq(SysAccount::getActive, true));
    }

    /**
     * 通过用户名获取用户详情
     */
    @Override
    public SysAccount getAccountByUsername(String username) {
        LambdaQueryWrapper<SysAccount> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysAccount::getUsername, username)
                .eq(SysAccount::getActive, true);
        return sysAccountMapper.selectOne(queryWrapper);
    }

    /**
     * 注册用户
     */
    @Override
    public boolean registerAccount(SysAccount account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        int insert = sysAccountMapper.insert(account);
        return insert >= 1;
    }

    /**
     * 注销用户
     */
    @Override
    public boolean logout() {
        SecurityContextHolder.clearContext();
        return true;
    }
}
