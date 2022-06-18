package uniacademia.phellipe.barbearia.barbearia.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uniacademia.phellipe.barbearia.barbearia.model.Agendamento;

import java.util.List;

public interface AgendamentoDAO extends JpaRepository<Agendamento, Long> {
    @Query(value = "SELECT a FROM Agendamento a WHERE a.usuario.id = :id")
    List<Agendamento> findAgendamentoByUsuarioId(Long id);
}
