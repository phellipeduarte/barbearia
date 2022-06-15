package uniacademia.phellipe.barbearia.barbearia.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniacademia.phellipe.barbearia.barbearia.DTO.CadastroUsuarioDTO;
import uniacademia.phellipe.barbearia.barbearia.model.Usuario;
import uniacademia.phellipe.barbearia.barbearia.service.UsuarioService;


@RestController
@RequestMapping("/api/usuario")
public class UsuarioResource {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public String test(){ return "funcionando"; }

    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody CadastroUsuarioDTO usuario) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.cadastrarUsuario(usuario));
    }
}
