package cn.mayanan.restapi.utils;

import cn.mayanan.restapi.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.lang.Collections;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 从请求头中获取 token
        String token = request.getHeader("Authorization");

        // 如果请求头中存在 token，进行验证
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // 去掉 "Bearer " 前缀
            try {
                // 解析并验证 token
                JwtUtil jwtUtil = new JwtUtil();
                Jws<Claims> claimsJws = jwtUtil.parseClaim(token);
                String username = claimsJws.getBody().get("username", String.class);

                // 创建 Authentication 对象，并设置为认证状态
                if (username != null) {
                    Authentication authentication = new UsernamePasswordAuthenticationToken(
                            username, null, Collections.emptyList()
                    );
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
                // 如果 token 无效，可以在这里记录错误信息或抛出异常
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json;charset=UTF-8");
//        httpServletResponse.getWriter().write("未登录，请先登录");

                // 创建错误响应对象
                ErrorResponse errorResponse = new ErrorResponse(401, "未登录，请先登录");
                // 将错误响应对象转换为json字符串
                // 使用ObjectMapper将对象转换为json字符串
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonResponse = objectMapper.writeValueAsString(errorResponse);
                // 将json字符串写入到response中
                response.getWriter().write(jsonResponse);
                return;
            }
        }
        // 如果没有 token 或者 token 验证失败，继续过滤链
        filterChain.doFilter(request, response);
    }
}



