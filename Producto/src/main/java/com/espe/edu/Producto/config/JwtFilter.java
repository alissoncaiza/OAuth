package com.espe.edu.Producto.config;

import com.espe.edu.Producto.service.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class JwtFilter extends GenericFilterBean {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String token = httpRequest.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            try {
                Claims claims = jwtUtil.validarToken(token);
                if (claims != null) {
                    System.out.println("Token válido. Claims: " + claims);
                    SecurityContextHolder.getContext().setAuthentication(jwtUtil.getAuthentication(claims));
                } else {
                    System.out.println("Claims es nulo.");
                }
            } catch (Exception e) {
                System.out.println("Error al validar token: " + e.getMessage());
                SecurityContextHolder.clearContext();
            }
        } else {
            System.out.println("Token no presente en la solicitud.");
        }

        chain.doFilter(request, response);
    }

}
