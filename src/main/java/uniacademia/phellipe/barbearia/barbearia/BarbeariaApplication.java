package uniacademia.phellipe.barbearia.barbearia;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import uniacademia.phellipe.barbearia.barbearia.DAO.EquipeDAO;
import uniacademia.phellipe.barbearia.barbearia.DAO.UsuarioDAO;
import uniacademia.phellipe.barbearia.barbearia.model.Equipe;
import uniacademia.phellipe.barbearia.barbearia.model.Usuario;

@SpringBootApplication
public class BarbeariaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BarbeariaApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Autowired
	EquipeDAO equipe;

	@Autowired
	UsuarioDAO users;

	@Autowired
	PasswordEncoder password;

	@Override
	public void run(String... args) throws Exception{
		Usuario usuario = new Usuario("phellipe","phellipe@duarte","lipe",password.encode("123"),true);
		users.save(usuario);
		Equipe membro = new Equipe("gustil", null);
		equipe.save(membro);
	}
}
