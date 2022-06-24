package uniacademia.phellipe.barbearia.barbearia.DTO;

public class AutorizacaoResponseDTO {

    private String usuario;
    private String token;

    public AutorizacaoResponseDTO(String usuario, String token){
        this.usuario = usuario;
        this.token = token;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getToken() {
        return token;
    }
}
