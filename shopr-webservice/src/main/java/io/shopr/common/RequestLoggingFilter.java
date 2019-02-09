package io.shopr.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RequestLoggingFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(RequestLoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(shouldNotFilter(request))
            return;

        log.info("****>> Incoming request. Method: [{}]. URI: [{}]" , request.getMethod(), request.getRequestURI());
        doFilter(request, response, filterChain);
        log.info("****<< Outgoing response. Status: {}, Content-type: {}", response.getStatus(), response.getHeader("Content-Type"));
    }
}
