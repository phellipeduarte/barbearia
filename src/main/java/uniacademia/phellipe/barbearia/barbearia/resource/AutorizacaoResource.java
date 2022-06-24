package uniacademia.phellipe.barbearia.barbearia.resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uniacademia.phellipe.barbearia.barbearia.DTO.AutorizacaoRequestDTO;
import uniacademia.phellipe.barbearia.barbearia.service.AutorizacaoService;

@RestController
public class AutorizacaoResource {

    @Autowired
    AutorizacaoService autorizacaoService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AutorizacaoRequestDTO requestDTO){
        return autorizacaoService.login(requestDTO);
    }
}
