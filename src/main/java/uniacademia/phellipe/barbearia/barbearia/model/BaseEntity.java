package uniacademia.phellipe.barbearia.barbearia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;

@Data
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

}
