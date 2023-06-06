package br.com.gui.racha.api.service;

import br.com.gui.racha.RachaApplication;
import br.com.gui.racha.model.entity.Role;
import br.com.gui.racha.model.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

@Service
public class JwtService {

    @Value("${security.jwt.expiration}")
    private String expiration;
    @Value("${security.jwt.key}")
    private String key;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    public String gerarToken(User user){
        long expString = Long.valueOf(expiration);

        LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expString);
        Date data = Date.from(dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant());

        HashMap<String, Object> claims = new HashMap<>();

//        System.out.println("dasdasdsadsadas: ");
//        System.out.println("Role: " + user.getRole().getId());
//
//        Role role = roleService.findById(user.getRole().getId());
        claims.put("role", user.getRole().getRole());
        claims.put("email", user.getEmail());

        return Jwts.builder()
                .setSubject(user.getEmail())
                .setExpiration(data)
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }


    private Claims obterClaims(String token) throws ExpiredJwtException {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }

    public boolean tokenValido( String token ){
        try{
            Claims claims = obterClaims(token);
            Date dataExpiracao = claims.getExpiration();
            LocalDateTime data = dataExpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(data);
        }catch (Exception e){
            return false;
        }
    }

    public String obterLoginUsuario(String token) throws ExpiredJwtException {
        return (String) obterClaims(token).getSubject();
    }

}
