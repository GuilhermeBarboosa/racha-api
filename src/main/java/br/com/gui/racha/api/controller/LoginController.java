package br.com.gui.racha.api.controller;

import br.com.gui.racha.api.exception.SenhaInvalidaException;
import br.com.gui.racha.api.service.JwtService;
import br.com.gui.racha.api.service.UserService;
import br.com.gui.racha.model.entity.User;
import br.com.gui.racha.model.input.LoginInput;
import br.com.gui.racha.model.output.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping
    public Token login(@RequestBody LoginInput loginInput) {
        try {
            User usuario = User.builder()
                    .email(loginInput.getEmail())
                    .senha(loginInput.getSenha()).build();

            User user = userService.autenticar(usuario);
            String token = jwtService.gerarToken(user);
            return new Token(usuario.getEmail(), token);

        } catch (SenhaInvalidaException | UsernameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
