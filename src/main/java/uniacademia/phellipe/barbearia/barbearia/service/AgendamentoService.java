package uniacademia.phellipe.barbearia.barbearia.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import uniacademia.phellipe.barbearia.barbearia.DAO.AgendamentoDAO;
import uniacademia.phellipe.barbearia.barbearia.DAO.UsuarioDAO;
import uniacademia.phellipe.barbearia.barbearia.DTO.AgendamentoDTO;
import uniacademia.phellipe.barbearia.barbearia.exceptions.EmptyFieldException;
import uniacademia.phellipe.barbearia.barbearia.model.Agendamento;
import uniacademia.phellipe.barbearia.barbearia.model.Usuario;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public record AgendamentoService(AgendamentoDAO agendamentoDAO) {

    public Agendamento agendar(AgendamentoDTO agendamentoDTO) throws Exception{

        if(agendamentoDTO.getDataAgendamento() == null){
            throw new EmptyFieldException("É preciso informar a data de agendamento");
        }
        else{
            Agendamento agendamento = new Agendamento(agendamentoDTO.getDataAgendamento(),
                    agendamentoDTO.getUsuario(),
                    agendamentoDTO.getTipoCorte(),
                    agendamentoDTO.getDescricao());
            return agendamentoDAO.save(agendamento);
        }
    }

    public List<Agendamento> listarAgendamentos(){
        return agendamentoDAO.findAll();
    }

    public List<Agendamento> listarAgendamentosPorUsuario(UsuarioDAO usuarioDAO){


        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();

        Optional<Usuario> usuario = usuarioDAO.findByUsuario(username);

        if(usuario.isPresent()){
            return agendamentoDAO.findAgendamentoByUsuarioId(usuario.get().getId());
        }
        return null;
    }

    public List<Agendamento> listarAgendamentosNoDia(){

        List<Agendamento> listaAgendamentos = listarAgendamentos();

        return (List<Agendamento>) listaAgendamentos.stream().filter(agendamento -> agendamento.getDataAgendamento().get(
                ChronoField.DAY_OF_YEAR) == LocalDate.now().get(ChronoField.DAY_OF_YEAR)).collect(Collectors.toList());
    }

    public List<Agendamento> listarAgendamentosNaSemana(){

        List<Agendamento> listaAgendamentos = listarAgendamentos();

        return (List<Agendamento>) listaAgendamentos.stream().filter(agendamento -> agendamento.getDataAgendamento().get(
                ChronoField.ALIGNED_WEEK_OF_YEAR) == LocalDate.now().get(ChronoField.ALIGNED_WEEK_OF_YEAR)).collect(Collectors.toList());
    }

    public List<Agendamento> listarAgendamentosNoMes(){

        List<Agendamento> listaAgendamentos = listarAgendamentos();

        return (List<Agendamento>) listaAgendamentos.stream().filter(agendamento -> agendamento.getDataAgendamento().getMonth()
                .equals(LocalDate.now().getMonth())).collect(Collectors.toList());
    }

    public int contarAgendamentosNoDia(){

        List<Agendamento> listaAgendamentosNoDia = listarAgendamentosNoDia();

        return listaAgendamentosNoDia.size();
    }

    public int contarAgendamentosNaSemana(){

        List<Agendamento> listaAgendamentosSemana = listarAgendamentosNaSemana();

        return listaAgendamentosSemana.size();
    }

    public int contarAgendamentosNoMes(){

        List<Agendamento> listaAgendamentosNoMes = listarAgendamentosNoMes();

        return listaAgendamentosNoMes.size();
    }
}
