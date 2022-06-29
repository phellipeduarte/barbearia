package uniacademia.phellipe.barbearia.barbearia.resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uniacademia.phellipe.barbearia.barbearia.DAO.UsuarioDAO;
import uniacademia.phellipe.barbearia.barbearia.DTO.AgendamentoDTO;
import uniacademia.phellipe.barbearia.barbearia.model.Agendamento;
import uniacademia.phellipe.barbearia.barbearia.service.AgendamentoService;

import java.util.List;

@RestController
@RequestMapping("/agendamento")
@PreAuthorize("isAuthenticated()")
public class AgendamentoResource {

    @Autowired
    UsuarioDAO usuarioDAO;
    @Autowired
    AgendamentoService agendamentoService;

    @GetMapping("/listar-todos")
    @PreAuthorize("hasHole('ADMIN')")
    public ResponseEntity<List<Agendamento>> listarAgendamentos(){
        return ResponseEntity.ok(agendamentoService.listarAgendamentos());
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Agendamento>> listarAgendamentosPorUsuario(){
        return ResponseEntity.ok(agendamentoService.listarAgendamentosPorUsuario(usuarioDAO));
    }

    @GetMapping("/listar/dia")
    @PreAuthorize("hasHole('ADMIN')")
    public ResponseEntity<List<Agendamento>> listarAgendamentosNoDia(){
        return ResponseEntity.ok(agendamentoService.listarAgendamentosNoDia());
    }

    @GetMapping("/contar/dia")
    @PreAuthorize("hasHole('ADMIN')")
    public ResponseEntity<Integer> contarAgendamentosNoDia() {
        return ResponseEntity.ok(agendamentoService.contarAgendamentosNoDia());
    }

    @GetMapping("/listar/semana")
    @PreAuthorize("hasHole('ADMIN')")
    public ResponseEntity<List<Agendamento>> listarAgendamentosNaSemana(){
        return ResponseEntity.ok(agendamentoService.listarAgendamentosNaSemana());
    }

    @GetMapping("/contar/semana")
    @PreAuthorize("hasHole('ADMIN')")
    public ResponseEntity<Integer> contarAgendamentosNaSemana(){
        return ResponseEntity.ok(agendamentoService.contarAgendamentosNaSemana());
    }

    @GetMapping("/listar/mes")
    @PreAuthorize("hasHole('ADMIN')")
    public ResponseEntity<List<Agendamento>> listarAgendamentosNoMes(){
        return ResponseEntity.ok(agendamentoService.listarAgendamentosNoMes());
    }

    @GetMapping("/contar/mes")
    @PreAuthorize("hasHole('ADMIN')")
    public ResponseEntity<Integer> contarAgendamentosNoMes(){
        return ResponseEntity.ok(agendamentoService.contarAgendamentosNoMes());
    }

    @PostMapping("/criar")
    public ResponseEntity<Agendamento> agendar(@RequestBody AgendamentoDTO agendamento) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(agendamentoService.agendar(agendamento));
    }
}
