package uniacademia.phellipe.barbearia.barbearia.DTO;
import lombok.Data;

@Data
public class CadastroUsuarioDTO {
    private String nome;
    private String email;
    private String usuario;
    private String senha;
    private boolean adm;
}
