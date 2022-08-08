package com.management.fresher.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.management.fresher.response.ResultResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration implements SecurityMemoryHolder {
    private String token;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new GeneratorTokenFilter("/api/tokenGenerator", this), BasicAuthenticationFilter.class)
                .addFilterBefore(new CustomAuthorizationFilter(this), GeneratorTokenFilter.class)
                .authorizeRequests()
                .anyRequest()
                .authenticated().and().exceptionHandling().authenticationEntryPoint(new AccessDenyEntryPoint());
        return http.build();
    }
    @Override
    public void setToken(String token) {
        this.token = token;
    }
    @Override
    public String getToken() {
        return token;
    }

    public class AccessDenyEntryPoint implements AuthenticationEntryPoint {
        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(403);
            ResultResponse resultResponse = new ResultResponse(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.toString());
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(response.getOutputStream(), resultResponse);
        }
    }
}
