package uniacademia.phellipe.barbearia.barbearia.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import uniacademia.phellipe.barbearia.barbearia.model.Usuario;

import java.util.Optional;

public interface UsuarioDAO extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsuario(String usuario);
}
