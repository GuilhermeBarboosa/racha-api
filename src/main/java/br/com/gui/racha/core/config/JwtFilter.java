package br.com.gui.racha.core.config;

import br.com.gui.racha.api.service.JwtService;
import br.com.gui.racha.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
public class JwtFilter {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Bean
    public OncePerRequestFilter init() {
        return new JwtAuthFilter(jwtService, userService);
    }
}
