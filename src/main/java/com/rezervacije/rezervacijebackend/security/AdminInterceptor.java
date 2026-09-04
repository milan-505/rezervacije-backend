package com.rezervacije.rezervacijebackend.security;

import com.rezervacije.rezervacijebackend.model.Uloga;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 *
 * @author Milan
 */
public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("idKorisnik") == null) {
            AuthInterceptor.odbij(response, HttpServletResponse.SC_UNAUTHORIZED, "Niste prijavljeni.");
            return false;
        }
        Object uloga = session.getAttribute("uloga");
        if (uloga != Uloga.ADMINISTRATOR) {
            AuthInterceptor.odbij(response, HttpServletResponse.SC_FORBIDDEN, "Nemate administratorska prava.");
            return false;
        }
        return true;
    }
}
