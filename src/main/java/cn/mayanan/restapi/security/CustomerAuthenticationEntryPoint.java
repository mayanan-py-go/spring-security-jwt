package cn.mayanan.restapi.security;

import cn.mayanan.restapi.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;

import java.io.IOException;

/**
 * 匿名未登录的时候访问,需要登录的资源的调用类
 * @author zzzgd
 */
// 注解的作用：将类标注为一个通用的spring管理的Bean
// java中注解的作用：将类标注为一个通用的spring管理的Bean
@Component
public class CustomerAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final View error;

    public CustomerAuthenticationEntryPoint(View error) {
        this.error = error;
    }

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        //设置response状态码，返回错误信息
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.setContentType("application/json;charset=UTF-8");
//        httpServletResponse.getWriter().write("未登录，请先登录");

        // 创建错误响应对象
        ErrorResponse errorResponse = new ErrorResponse(401, "未登录，请先登录");
        // 将错误响应对象转换为json字符串
        // 使用ObjectMapper将对象转换为json字符串
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(errorResponse);
        // 将json字符串写入到response中
        httpServletResponse.getWriter().write(jsonResponse);

    }
}





