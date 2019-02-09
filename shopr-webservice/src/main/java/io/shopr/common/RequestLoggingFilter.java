package io.shopr.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
public class RequestLoggingFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(RequestLoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(shouldNotFilter(request))
            return;
        StopWatch stopWatch = new StopWatch(UUID.randomUUID().toString());
        stopWatch.start(Thread.currentThread().getName());
        doFilter(request, response, filterChain);
        stopWatch.stop();
        log.info(";; Processed [{}] in [{}] millis for URI: [{}] - (taskname = {})",
                request.getMethod(),
                stopWatch.getLastTaskTimeMillis(),
                request.getRequestURI(),
                stopWatch.getLastTaskName());

/*        log.info("****>> Incoming request. Method: [{}]. URI: [{}]" , request.getMethod(), request.getRequestURI());
        doFilter(request, response, filterChain);
        log.info("****<< Outgoing response. Status: {}, Content-type: {}", response.getStatus(), response.getHeader("Content-Type"));*/
    }
}
