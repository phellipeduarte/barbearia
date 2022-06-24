package uniacademia.phellipe.barbearia.barbearia.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import uniacademia.phellipe.barbearia.barbearia.DAO.UsuarioDAO;
import uniacademia.phellipe.barbearia.barbearia.filter.JWTokenFilter;

import javax.servlet.http.HttpServletResponse;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder crypto() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private JWTokenFilter jwTokenFilter;

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    protected void configure(AuthenticationManagerBuilder authenManager) throws Exception {
        authenManager.userDetailsService(usuario -> usuarioDAO.findByUsuario(usuario)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado")));
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();
        http.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and();

        http.exceptionHandling().authenticationEntryPoint(
                ((request, response, authException) -> {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                            authException.getMessage());
                }));

        http.addFilterBefore(jwTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}