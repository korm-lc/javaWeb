package com.example.filter;

import com.example.utils.CurrentHolder;
import com.example.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
@Slf4j
public class TokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        log.info("拦截到请求，开始处理");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //1.获取到请求路径
        String requestURI = request.getRequestURI();//不包含协议，ip和端口，只是路径

        //2.判断请求路径是否为登录接口，如果路径中包含/login，则放行
        if (requestURI.contains("/login")) {

            filterChain.doFilter(request, response);
            return;
        }
        //3.获取请求头中的token
        String token = request.getHeader("token");
        //4.判断token是否存在
        if (token == null|| token.isEmpty()) {
            //4.1如果不存在，则响应401
            log.info("令牌为空，请先登录");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        //5.如果存在，校验令牌

        //5.1校验令牌
        try {
            Integer empId = Integer.parseInt(JwtUtils.parseToken(token).get("id").toString());
            CurrentHolder.set(empId);
            log.info("当前登录员工id：{},将其存入ThreadLocal",empId);

        }catch (Exception e){
            log.info("令牌校验失败，请重新登录");
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        //5.如果校验通过，则放行
        log.info("过滤器通过，放行");
        filterChain.doFilter(request, response);

        CurrentHolder.remove();


    }
}
