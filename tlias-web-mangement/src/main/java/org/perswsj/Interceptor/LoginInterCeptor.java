package org.perswsj.Interceptor;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.perswsj.utils.CurrentHolder;
import org.perswsj.utils.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class LoginInterCeptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求路径
        String requestURI = request.getRequestURI();
        // 获取Token
        String token = request.getHeader("token");
        // 检查Token是否为空
        if (token == null || token.isEmpty()) {
            // 返回 401 状态码
            log.info("用户未登录，返回 401 状态码");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("用户未登录，请先登录！");
            return false;
        }
        // 解析Token
        try {
            Claims claims = JwtUtil.parseToken(token);
            // 从Claims中获取用户ID并设置到线程本地存储
            Integer id = claims.get("id", Integer.class);
            CurrentHolder.setCurrentId(id);
        } catch (Exception e) {
            // 返回 401 状态码
            log.info("令牌非法，返回 401 状态码");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("令牌非法，请重新登录！");
            return false;
        }
        // 放行
        log.info("令牌合法，放行");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清除线程本地存储中的用户ID
        CurrentHolder.remove();
    }
}
