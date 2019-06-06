package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder encoder() {
        return new StandardPasswordEncoder("53cr3t");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //* auth.ldapAuthentication()
        //*     .userSearchBase("ou=people")
        //*     .userSearchFilter("(uid={0})")
        //*     .groupSearchBase("ou=groups")
        //*     .groupSearchFilter("member={0}")
        //*     .passwordCompare()
        //*     .passwordEncoder(new BCryptPasswordEncoder())
        //*     .passwordAttribute("password");

        auth.userDetailsService(userDetailsService)
            .passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
          .authorizeRequests()
            .antMatchers("/design", "/orders")
              .hasRole("hasRole('ROLE_USER')")
            .antMatchers("/", "/**").access("permitAll")
          .and()
            .formLogin()
              .loginPage("/login")
          .and()
            .formLogin()
              .loginPage("/login")
              .loginProcessingUrl("/authenticate")
              .usernameParameter("user")
              .passwordParameter("pwd")
          .and()
            .formLogin()
              .loginPage("/login")
              .defaultSuccessUrl("/design", true)
          .and()
            .logout()
              .logoutSuccessUrl("/")
          ;
    }
}