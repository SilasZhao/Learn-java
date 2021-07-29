package com.example.demo.filter;

import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.UUID;

@WebFilter(urlPatterns = "/*")
@Order(1)
public class TraceIdFilter implements Filter {
    /**
     * TraceId 常量
     */
    private static final String TRACE_ID = "traceId";

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        //尝试从请求中获取TraceId信息
        String traceId = servletRequest.getParameter(TRACE_ID);

        //为空设置默认值
        if(StringUtils.isEmpty(traceId)){
            traceId = UUID.randomUUID().toString();
        }
        //在MDC中放入traceId MDC是和线程绑定的一个map
        MDC.put(TRACE_ID,traceId);

        filterChain.doFilter(servletRequest,servletResponse);
    }
}
