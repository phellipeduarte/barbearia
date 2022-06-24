package uniacademia.phellipe.barbearia.barbearia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "Usuario")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Usuario extends BaseEntity implements UserDetails {

    @Column(length = 80, nullable = false)
    private String nome;

    @Column(length = 80, nullable = false)
    private String email;

    @Column(length = 80, unique = true, nullable = false)
    private String usuario;

    @Column(length = 100, nullable = false)
    private String senha;

    private boolean adm;

    public List<String> getRoles(){
        ArrayList<String> roles = new ArrayList<>();
        roles.add("ROLE_USER");
        if (adm){
            roles.add("ROLE_ADMIN");
        }
        return roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return usuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
