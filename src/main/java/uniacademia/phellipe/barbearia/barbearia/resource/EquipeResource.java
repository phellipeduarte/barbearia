package uniacademia.phellipe.barbearia.barbearia.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uniacademia.phellipe.barbearia.barbearia.DTO.CadastroEquipeDTO;
import uniacademia.phellipe.barbearia.barbearia.model.Equipe;
import uniacademia.phellipe.barbearia.barbearia.service.EquipeService;

import java.util.List;

@RestController
@RequestMapping("/equipe")
public class EquipeResource {

    @Autowired
    EquipeService equipeService;

    @GetMapping()
    @PreAuthorize("hasHole('ADMIN')")
    private ResponseEntity<List<Equipe>> listarMembrosEquipe(){
        return ResponseEntity.ok(equipeService.listar());
    }

    @PostMapping("/criar")
    @PreAuthorize("hasHole('ADMIN')")
    private ResponseEntity<Equipe> criarMembroEquipe(@RequestBody CadastroEquipeDTO equipeDTO){
        return ResponseEntity.ok(equipeService.criar(equipeDTO));
    }

}
