package com.rezervacije.rezervacijebackend.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 *
 * @author Milan
 */
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true; 
        }
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("idKorisnik") == null) {
            odbij(response, HttpServletResponse.SC_UNAUTHORIZED, "Niste prijavljeni.");
            return false;
        }
        return true;
    }

    static void odbij(HttpServletResponse response, int status, String poruka) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"message\":\"" + poruka + "\"}");
    }
}
