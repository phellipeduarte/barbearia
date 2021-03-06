package uniacademia.phellipe.barbearia.barbearia.model;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import uniacademia.phellipe.barbearia.barbearia.enums.TipoCorteEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Agendamento")
@EqualsAndHashCode(callSuper = true)
public class Agendamento extends BaseEntity {

    @Column(nullable = false)
    private LocalDateTime dataAgendamento;

    @ManyToOne()
    private Usuario usuario;

    @ManyToOne()
    private Equipe membroEquipe;

    @Column(nullable = false)
    private TipoCorteEnum tipoCorte;

    @Column()
    private String descricao;

}
