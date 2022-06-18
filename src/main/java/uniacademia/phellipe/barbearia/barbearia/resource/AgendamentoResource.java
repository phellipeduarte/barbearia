package uniacademia.phellipe.barbearia.barbearia.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniacademia.phellipe.barbearia.barbearia.DTO.AgendamentoDTO;
import uniacademia.phellipe.barbearia.barbearia.model.Agendamento;
import uniacademia.phellipe.barbearia.barbearia.service.AgendamentoService;

import java.util.List;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoResource {

    @Autowired
    AgendamentoService agendamentoService;

    @GetMapping("/listar")
    public ResponseEntity<List<Agendamento>> listarAgendamentos(){
        return ResponseEntity.ok(agendamentoService.listarAgendamentos());
    }

    @GetMapping("/listar/{usuarioId}")
    public ResponseEntity<List<Agendamento>> listarAgendamentosPorUsuario(@PathVariable String usuarioId){
        return ResponseEntity.ok(agendamentoService.listarAgendamentosPorUsuario(usuarioId));
    }

    @PostMapping("/criar")
    public ResponseEntity<Agendamento> agendar(@RequestBody AgendamentoDTO agendamento) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(agendamentoService.agendar(agendamento));
    }
}
