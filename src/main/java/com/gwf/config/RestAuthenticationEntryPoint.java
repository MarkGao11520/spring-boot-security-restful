package com.gwf.config;

import com.gwf.results.AuthErrorEnum;
import com.gwf.results.ResultGenerator;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by gaowenfeng on 2017/8/9.
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint{
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        //TODO 捕获AuthenticationException中的message，并封装成自定义异常抛出
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(ResultGenerator.genUnAuthResult(AuthErrorEnum.AUTH_HEADER_ERROR.getMessage()).toString());
//        httpServletResponse.getWriter().append("weidenglu");
    }
}
