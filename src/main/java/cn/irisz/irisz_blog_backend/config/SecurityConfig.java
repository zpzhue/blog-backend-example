package cn.irisz.irisz_blog_backend.config;

import cn.irisz.irisz_blog_backend.common.exception.BusinessException;
import cn.irisz.irisz_blog_backend.common.result.ResponseEnum;
import cn.irisz.irisz_blog_backend.entity.SysAccount;
import cn.irisz.irisz_blog_backend.service.SysAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * <p>
 * SecurityConfig类
 * </p>
 *
 * @author 朱志鹏
 * @since 2021/9/5 0005
 */

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    SysAccountService sysAccountService;

    @Resource
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @Resource
    ApiAccessDeniedHandler apiAccessDeniedHandler;

    @Resource
    ApiAuthenticationEntryPoint apiAuthenticationEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public UserDetailsService userDetailsService(){
        return username -> {
            SysAccount sysAccount = sysAccountService.getAccountByUsername(username);
            if (sysAccount == null){
                throw new BusinessException(ResponseEnum.USER_NOT_EXIST);
            }
            return sysAccount;
        };
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 放行白名单
        web.ignoring().antMatchers(
                "/account/login",
                "/account/register",
                "/doc.html",
                "/webjars/**",
                "/v3/**",
                "/swagger-ui.html",
                "/swagger-resources/**"
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                // 禁用session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                // 禁用缓存
                .headers().cacheControl();

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling().accessDeniedHandler(apiAccessDeniedHandler).authenticationEntryPoint(apiAuthenticationEntryPoint);
    }
}
