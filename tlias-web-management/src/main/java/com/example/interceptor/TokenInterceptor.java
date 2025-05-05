package com.example.interceptor;


import com.example.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
//令牌校验的拦截器
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //3.获取请求头中的token

        String token =request.getHeader("token");
        //4.判断token是否存在，如果不存在则返回401

        if (token==null||token.isEmpty()){
            log.info("无令牌，无法校验");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        //5.如果token存在，校验令牌，入不通过返回401状态码

        try {
            JwtUtils.parseToken(token);
        }catch (Exception e){
            log.info("令牌非法，校验无法通过");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        //6.token校验通过，放行
        log.info("令牌合法，通过");
        return true;
    }

}
