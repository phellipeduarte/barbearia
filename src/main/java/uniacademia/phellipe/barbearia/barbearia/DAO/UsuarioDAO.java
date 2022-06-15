package uniacademia.phellipe.barbearia.barbearia.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import uniacademia.phellipe.barbearia.barbearia.model.Usuario;

public interface UsuarioDAO extends JpaRepository<Usuario, Long> {

    Usuario findByUsuario(String usuario);
}
