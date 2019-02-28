package cat.udl.eps.entsoftarch.webmemoryapi.config;

import cat.udl.eps.entsoftarch.webmemoryapi.domain.Admin;
import cat.udl.eps.entsoftarch.webmemoryapi.domain.Player;
import cat.udl.eps.entsoftarch.webmemoryapi.domain.User;
import cat.udl.eps.entsoftarch.webmemoryapi.repository.AdminRepository;
import cat.udl.eps.entsoftarch.webmemoryapi.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;

@Configuration
public class AuthenticationConfig extends GlobalAuthenticationConfigurerAdapter {

  @Value("${default-password}")
  String defaultPassword;

  @Autowired BasicUserDetailsService basicUserDetailsService;
  @Autowired AdminRepository adminRepository;
  @Autowired PlayerRepository playerRepository;

  @Override
  public void init(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .userDetailsService(basicUserDetailsService)
        .passwordEncoder(User.passwordEncoder);

    if (!adminRepository.existsById("admin")) {
      Admin admin = new Admin();
      admin.setEmail("admin@webmemory.org");
      admin.setUsername("admin");
      admin.setPassword(defaultPassword);
      admin.encodePassword();
      adminRepository.save(admin);
    }
    if (!playerRepository.existsById("user")) {
      Player user = new Player();
      user.setEmail("user@webmemory.org");
      user.setUsername("user");
      user.setPassword(defaultPassword);
      user.encodePassword();
      playerRepository.save(user);
    }
  }
}
