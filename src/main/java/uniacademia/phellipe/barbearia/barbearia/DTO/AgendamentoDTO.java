package uniacademia.phellipe.barbearia.barbearia.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uniacademia.phellipe.barbearia.barbearia.enums.TipoCorteEnum;
import uniacademia.phellipe.barbearia.barbearia.model.Usuario;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoDTO {
    private Date dataAgendamento;
    private Usuario usuario;
    private TipoCorteEnum tipoCorte;
    private String descricao;
}
