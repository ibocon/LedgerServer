package com.ibocon.ledger.config.auth.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(
        HttpServletRequest httpServletRequest, 
        HttpServletResponse httpServletResponse, 
        AuthenticationException authenticationException
    )
    throws IOException, ServletException {
        log.info("Responding with unauthorized error. Message - {}", authenticationException.getMessage());
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, authenticationException.getLocalizedMessage());
    }

}