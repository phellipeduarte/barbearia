package uniacademia.phellipe.barbearia.barbearia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Equipe")
@EqualsAndHashCode(callSuper = true)
public class Equipe extends BaseEntity{

    @Column(unique = true, nullable = false, length = 20)
    private String nome;

    @OneToMany()
    private Set<Agendamento> agendamentos;
}
