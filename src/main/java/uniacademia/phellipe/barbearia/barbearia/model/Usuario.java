package uniacademia.phellipe.barbearia.barbearia.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Usuario")
@EqualsAndHashCode(callSuper = true)
public class Usuario extends BaseEntity{

    @Column(length = 80, nullable = false)
    private String nome;

    @Column(length = 80, nullable = false)
    private String email;

    @Column(length = 80, unique = true, nullable = false)
    private String usuario;

    @Column(length = 100, nullable = false)
    private String senha;

    private boolean adm;

}
