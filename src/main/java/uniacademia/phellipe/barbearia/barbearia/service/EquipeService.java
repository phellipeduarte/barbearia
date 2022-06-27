package uniacademia.phellipe.barbearia.barbearia.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uniacademia.phellipe.barbearia.barbearia.DAO.EquipeDAO;
import uniacademia.phellipe.barbearia.barbearia.DTO.CadastroEquipeDTO;
import uniacademia.phellipe.barbearia.barbearia.exceptions.EmptyFieldException;
import uniacademia.phellipe.barbearia.barbearia.model.Equipe;

import java.util.List;

@Service
public record EquipeService(EquipeDAO equipeDAO, ModelMapper mapper) {

    public List<Equipe> listar(){
        return equipeDAO.findAll();
    }

    public Equipe criar(CadastroEquipeDTO equipeDTO) {
        if (equipeDTO.getNome().isEmpty()){
            throw new EmptyFieldException("É preciso informar os dados do membro da equipe");
        }
        else{
            Equipe membro = mapper.map(equipeDTO, Equipe.class);
            return equipeDAO.save(membro);
        }
    }
}
