package uniacademia.phellipe.barbearia.barbearia.service;

import org.springframework.stereotype.Service;
import uniacademia.phellipe.barbearia.barbearia.DAO.AgendamentoDAO;
import uniacademia.phellipe.barbearia.barbearia.DTO.AgendamentoDTO;
import uniacademia.phellipe.barbearia.barbearia.exceptions.EmptyFieldException;
import uniacademia.phellipe.barbearia.barbearia.model.Agendamento;

import java.util.List;

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

    public List<Agendamento> listarAgendamentosPorUsuario(String usuarioId){

        long id = Long.valueOf(usuarioId);

        List<Agendamento> agendamentos = agendamentoDAO.findAgendamentoByUsuarioId(id);
        return agendamentos;
    }
}
