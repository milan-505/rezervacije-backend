package com.rezervacije.rezervacijebackend.config;

import com.rezervacije.rezervacijebackend.security.AdminInterceptor;
import com.rezervacije.rezervacijebackend.security.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Zamenjuje pojedinacne @CrossOrigin anotacije na kontrolerima - CORS mora
 * da dozvoli "allowCredentials", jer sesija (JSESSIONID kolacic) putuje
 * izmedju frontenda (localhost:3000) i backend-a (localhost:8080) samo ako
 * je to eksplicitno dozvoljeno.
 *
 * Ovde su i registrovani interceptori koji predstavljaju nasu (jednostavnu,
 * bez Spring Security-ja) autentikaciju/autorizaciju zasnovanu na sesiji.
 *
 * @author Milan
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Endpoint-i dostupni samo administratoru
        registry.addInterceptor(new AdminInterceptor())
                .addPathPatterns(
                        "/dogadjaji/add",
                        "/dogadjaji/update/**",
                        "/dogadjaji/delete/**",
                        "/rezervacije/pending",
                        "/rezervacije/all",
                        "/rezervacije/export/pdf",
                        "/rezervacije/*/potvrdi",
                        "/rezervacije/*/odbij",
                        "/korisnici/all"
                );

        // Endpoint-i dostupni svakom prijavljenom korisniku
        registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns(
                        "/rezervacije/add",
                        "/rezervacije/*/otkazi",
                        "/rezervacije/korisnik/**",
                        "/korisnici/session"
                );
    }
}
