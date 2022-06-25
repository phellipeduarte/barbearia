package uniacademia.phellipe.barbearia.barbearia.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uniacademia.phellipe.barbearia.barbearia.enums.TipoCorteEnum;
import uniacademia.phellipe.barbearia.barbearia.model.Usuario;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoDTO {
    private LocalDateTime dataAgendamento;
    private Usuario usuario;
    private TipoCorteEnum tipoCorte;
    private String descricao;
}
