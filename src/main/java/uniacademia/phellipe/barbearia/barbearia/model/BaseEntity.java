package uniacademia.phellipe.barbearia.barbearia.model;

import lombok.Data;
import java.util.Date;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;

@Data
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @CreatedDate
    private Date dataCadastro;

    @LastModifiedDate
    private Date dataModificacao;

}
