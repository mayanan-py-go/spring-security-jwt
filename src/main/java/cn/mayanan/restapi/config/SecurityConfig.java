package cn.mayanan.restapi.config;

import cn.mayanan.restapi.security.CustomerAuthenticationEntryPoint;
import cn.mayanan.restapi.utils.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// 标注配置类，表示该类包含了spring配置
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private CustomerAuthenticationEntryPoint customerAuthenticationEntryPoint;
    // 注入 JwtAuthenticationFilter
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // 禁用csrf，仅限开发时使用
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/login").permitAll()
                        .anyRequest().authenticated()  // 其他请求需要认证
                )
                // 禁用session
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // 处理未认证时的错误
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(customerAuthenticationEntryPoint)
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);  // 在 UsernamePasswordAuthenticationFilter 前添加 JWT 过滤器
        return http.build();
    }
}









