package uniacademia.phellipe.barbearia.barbearia.filter;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import uniacademia.phellipe.barbearia.barbearia.model.Usuario;

import java.util.Date;

@Component
public class JWToken {
    private static final Logger LOGGER = LoggerFactory.getLogger(JWToken.class);
    private static final long EXPIRE_DURATION = 24*60*60*1000; // 24h

    private String secretKey = "abcdefg";

    public String gerarToken(Usuario usuario){
        return Jwts.builder()
                .setSubject(usuario.getId() + "," + usuario.getUsername())
                .setIssuer("CodeJava")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public boolean validarToken(String token){
        try{
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        }catch (ExpiredJwtException exception){
            LOGGER.error("JWT expirado", exception);
        }catch (IllegalArgumentException exception){
            LOGGER.error("JWT está vazio ou com espaços em branco", exception);
        }catch (MalformedJwtException exception){
            LOGGER.error("JWT é inválido", exception);
        }catch (UnsupportedJwtException exception){
            LOGGER.error("JWT não é suportado", exception);
        }catch (SignatureException exception){
            LOGGER.error("Validação falhou");
        }

        return false;
    }

    public String getSubject(String token){
        return parseClaims(token).getSubject();
    }

    public Claims parseClaims(String token){
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }
}
