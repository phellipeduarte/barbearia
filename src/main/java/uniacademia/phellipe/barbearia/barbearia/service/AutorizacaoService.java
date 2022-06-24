package uniacademia.phellipe.barbearia.barbearia.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import uniacademia.phellipe.barbearia.barbearia.DTO.AutorizacaoRequestDTO;
import uniacademia.phellipe.barbearia.barbearia.DTO.AutorizacaoResponseDTO;
import uniacademia.phellipe.barbearia.barbearia.filter.JWToken;
import uniacademia.phellipe.barbearia.barbearia.model.Usuario;

@Service
public class AutorizacaoService {


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWToken JWTokenUtil;


    public ResponseEntity<?> login(AutorizacaoRequestDTO requestDTO){
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestDTO.getUsuario(), requestDTO.getSenha())
            );

            Usuario usuario = (Usuario) authentication.getPrincipal();

            String token = JWTokenUtil.gerarToken(usuario);

            AutorizacaoResponseDTO response = new AutorizacaoResponseDTO(usuario.getUsername(), token);

            return ResponseEntity.ok(response);

        }catch (BadCredentialsException exception){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
