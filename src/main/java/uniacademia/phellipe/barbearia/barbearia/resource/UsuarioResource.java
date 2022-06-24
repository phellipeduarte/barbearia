package uniacademia.phellipe.barbearia.barbearia.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uniacademia.phellipe.barbearia.barbearia.DTO.CadastroUsuarioDTO;
import uniacademia.phellipe.barbearia.barbearia.model.Usuario;
import uniacademia.phellipe.barbearia.barbearia.service.UsuarioService;

import java.util.List;

@PreAuthorize("hasHole('USER')")
@RestController
@RequestMapping("/usuario")
public class UsuarioResource {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public String test(){ return "funcionando"; }

    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listarUsuarios(){
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @PostMapping("/cadastrar")
    @PreAuthorize("hasHole('ADMIN')")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody CadastroUsuarioDTO usuario) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.cadastrarUsuario(usuario));
    }
}
