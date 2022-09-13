package cn.irisz.irisz_blog_backend.config;

import cn.irisz.irisz_blog_backend.common.exception.BusinessException;
import cn.irisz.irisz_blog_backend.common.result.ResponseEnum;
import cn.irisz.irisz_blog_backend.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * JwtAuthenticationFilter类
 * </p>
 *
 * @author 朱志鹏
 * @since 2021/9/5 0005
 */

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Value("${jwt.token-header}")
    private String tokenHeader;
    @Value("${jwt.token-start-with}")
    private String tokenStartWith;

    @Resource
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = httpServletRequest.getHeader(tokenHeader);

        if (!StringUtils.isEmpty(authHeader) && authHeader.startsWith(tokenStartWith)){
            String token = authHeader.substring(tokenStartWith.length());
            String username = JwtUtils.getUsername(token);
            if (!StringUtils.isEmpty(username) && null == SecurityContextHolder.getContext().getAuthentication()){
                // 已登录
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                // token是否有效
                if (JwtUtils.checkToken(token)){
                    //把用户对象设置到SpringSecurity全局上下文中
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
