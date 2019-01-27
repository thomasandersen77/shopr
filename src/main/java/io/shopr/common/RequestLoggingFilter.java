package io.shopr.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@WebFilter(urlPatterns = "/*")
public class RequestLoggingFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(RequestLoggingFilter.class);
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(request instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            log.info("****>> Incoming request. Method: [{}]. URI: [{}]" , httpServletRequest.getMethod(), httpServletRequest.getRequestURI());
        }
        chain.doFilter(request, response);
        if(response instanceof HttpServletResponse){
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            log.info("****<< Outgoing response. Status = {}, Accept = {}", httpServletResponse.getStatus(), httpServletResponse.getHeader("Accept"));
        }
    }
}
