package uniacademia.phellipe.barbearia.barbearia.model;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import uniacademia.phellipe.barbearia.barbearia.enums.TipoCorteEnum;
import uniacademia.phellipe.barbearia.barbearia.model.Usuario;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Agendamento")
@EqualsAndHashCode(callSuper = true)
public class Agendamento extends BaseEntity {

    @Column(nullable = false)
    private Date dataAgendamento;

    @ManyToOne()
    private Usuario usuario;

    @Column(nullable = false)
    private TipoCorteEnum tipoCorte;

    @Column()
    private String descricao;

}
