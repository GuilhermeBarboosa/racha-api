package br.com.gui.racha.api.controller;

import br.com.gui.racha.api.exception.SenhaInvalidaException;
import br.com.gui.racha.api.service.JwtService;
import br.com.gui.racha.api.service.UserService;
import br.com.gui.racha.model.entity.User;
import br.com.gui.racha.model.input.LoginInput;
import br.com.gui.racha.model.output.Token;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
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

    @PostMapping("/verifyToken")
    public boolean verifyToken(@RequestBody String token) {
        try {
            return jwtService.tokenValido(token);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @PostMapping("/obterClaims")
    public Claims obterClaims(@RequestBody String token) {
        try {
            return jwtService.obterClaims(token);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
