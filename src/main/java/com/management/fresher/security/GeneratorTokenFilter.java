package com.management.fresher.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.management.fresher.response.ResultResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GeneratorTokenFilter extends GenericFilterBean {
    private final String filterProcessesUrl;
    private final SecurityMemoryHolder memoryHolder;
    public GeneratorTokenFilter(String filterProcessesUrl, SecurityMemoryHolder memoryHolder) {
        this.filterProcessesUrl = filterProcessesUrl;
        this.memoryHolder = memoryHolder;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if(request.getServletPath().equals(filterProcessesUrl)){
            Map<String, String> map = new HashMap<>();
            String token = UUID.randomUUID().toString();
            memoryHolder.setToken(token);
            map.put("token", token);
            ResultResponse resultResponse = new ResultResponse(HttpStatus.OK.value(), HttpStatus.OK.toString(), map);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(response.getOutputStream(), resultResponse);
        } else filterChain.doFilter(servletRequest, servletResponse);
    }
}
