package uniacademia.phellipe.barbearia.barbearia.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import uniacademia.phellipe.barbearia.barbearia.model.Usuario;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTokenFilter extends OncePerRequestFilter{

    @Autowired
    private JWToken JWToken;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if(!existeHeader(request)){
            filterChain.doFilter(request, response);
            return;
        }

        String token = getToken(request);

        if(!JWToken.validarToken(token)){
            filterChain.doFilter(request, response);
            return;
        }

        setAuthenticationContext(token, request);
        filterChain.doFilter(request, response);
    }

    private UserDetails getUserDetails(String token){
        Usuario userDetails = new Usuario();
        String[] subjectArray = JWToken.getSubject(token).split(",");

        userDetails.setId(Integer.parseInt(subjectArray[0]));
        userDetails.setUsuario(subjectArray[1]);

        return userDetails;
    }

    private void setAuthenticationContext(String token, HttpServletRequest request){
        UserDetails userDetails = getUserDetails(token);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, null);

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private boolean existeHeader(HttpServletRequest request){
        String header = request.getHeader("Authorization");

        if(ObjectUtils.isEmpty(header) || !header.startsWith("Bearer")){
            return false;
        }
        return true;
    }

    private String getToken(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        String token = header.split(" ")[1].trim();

        return token;
    }
}
