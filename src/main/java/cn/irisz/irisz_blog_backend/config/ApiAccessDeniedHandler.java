package cn.irisz.irisz_blog_backend.config;

import cn.irisz.irisz_blog_backend.common.result.R;
import cn.irisz.irisz_blog_backend.common.result.ResponseEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>
 * APIAccessDeniedHandler类
 * </p>
 *
 * @author 朱志鹏
 * @since 2021/9/5 0005
 */
@Component
public class ApiAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write(new ObjectMapper().writeValueAsString(R.setResult(ResponseEnum.AUTHENTICATION_ERROR)));
        writer.flush();
        writer.close();
    }
}
