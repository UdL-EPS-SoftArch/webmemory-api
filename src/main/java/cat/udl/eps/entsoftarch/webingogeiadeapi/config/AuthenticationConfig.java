package cat.udl.eps.entsoftarch.webingogeiadeapi.config;

import cat.udl.eps.entsoftarch.webingogeiadeapi.domain.Admin;
import cat.udl.eps.entsoftarch.webingogeiadeapi.domain.Player;
import cat.udl.eps.entsoftarch.webingogeiadeapi.domain.User;
import cat.udl.eps.entsoftarch.webingogeiadeapi.repository.AdminRepository;
import cat.udl.eps.entsoftarch.webingogeiadeapi.repository.PlayerRepository;
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
      admin.setEmail("admin@webingo.org");
      admin.setUsername("admin");
      admin.setPassword(defaultPassword);
      admin.encodePassword();
      adminRepository.save(admin);
    }
    if (!playerRepository.existsById("user")) {
      Player user = new Player();
      user.setEmail("user@webingo.org");
      user.setUsername("user");
      user.setPassword(defaultPassword);
      user.encodePassword();
      playerRepository.save(user);
    }
  }
}