package cn.irisz.irisz_blog_backend.config;

import cn.irisz.irisz_blog_backend.common.result.R;
import cn.irisz.irisz_blog_backend.common.result.ResponseEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>
 * ApiAuthenticationEntryPoint类
 * </p>
 *
 * @author 朱志鹏
 * @since 2021/9/5 0005
 */
@Component
public class ApiAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Value("${jwt.token-header}")
    private String tokenHeader;

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        PrintWriter writer = httpServletResponse.getWriter();

        String token = httpServletRequest.getHeader(tokenHeader);
        if(token == null || "".equals(token)){
            writer.write(new ObjectMapper().writeValueAsString(R.setResult(ResponseEnum.LOGIN_AUTH_ERROR)));
        }else {
            writer.write(new ObjectMapper().writeValueAsString(R.setResult(ResponseEnum.LOGIN_TOKEN_CHECK_ERROR)));
        }
        writer.flush();
        writer.close();
    }
}
