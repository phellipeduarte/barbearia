package uniacademia.phellipe.barbearia.barbearia.resource;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import uniacademia.phellipe.barbearia.barbearia.DAO.UsuarioDAO;
import uniacademia.phellipe.barbearia.barbearia.DTO.AgendamentoDTO;
import uniacademia.phellipe.barbearia.barbearia.model.Agendamento;
import uniacademia.phellipe.barbearia.barbearia.service.AgendamentoService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoResource {

    @Autowired
    UsuarioDAO usuarioDAO;
    @Autowired
    AgendamentoService agendamentoService;

    @GetMapping("/listar-todos")
    public ResponseEntity<List<Agendamento>> listarAgendamentos(){
        return ResponseEntity.ok(agendamentoService.listarAgendamentos());
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Agendamento>> listarAgendamentosPorUsuario(){
        return ResponseEntity.ok(agendamentoService.listarAgendamentosPorUsuario(usuarioDAO));
    }

    @GetMapping("/listar/dia")
    public ResponseEntity<List<Agendamento>> listarAgendamentosNoDia(){
        return ResponseEntity.ok(agendamentoService.listarAgendamentosNoDia());
    }

    @GetMapping("/listar/semana")
    public ResponseEntity<List<Agendamento>> listarAgendamentosNaSemana(){
        return ResponseEntity.ok(agendamentoService.listarAgendamentosNaSemana());
    }

    @GetMapping("/listar/mes")
    public ResponseEntity<List<Agendamento>> listarAgendamentosNoMes(){
        return ResponseEntity.ok(agendamentoService.listarAgendamentosNoMes());
    }

    @PostMapping("/criar")
    public ResponseEntity<Agendamento> agendar(@RequestBody AgendamentoDTO agendamento) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(agendamentoService.agendar(agendamento));
    }
}
