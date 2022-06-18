package uniacademia.phellipe.barbearia.barbearia.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uniacademia.phellipe.barbearia.barbearia.DAO.UsuarioDAO;
import uniacademia.phellipe.barbearia.barbearia.DTO.CadastroUsuarioDTO;
import uniacademia.phellipe.barbearia.barbearia.exceptions.EmptyFieldException;
import uniacademia.phellipe.barbearia.barbearia.model.Usuario;

import java.util.List;

@Service
public record UsuarioService(UsuarioDAO usuarioDAO, ModelMapper mapper) {

    public Usuario cadastrarUsuario(CadastroUsuarioDTO usuarioDTO) throws Exception {

        if (usuarioDTO.getUsuario().isEmpty()){
            throw new EmptyFieldException("É preciso informar os dados do usuário");
        }
        else{
            Usuario usuario = mapper.map(usuarioDTO, Usuario.class);
            return usuarioDAO.save(usuario);
        }
    }

    public List<Usuario> listarUsuarios(){
        return usuarioDAO.findAll();
    }

}
