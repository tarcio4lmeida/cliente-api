package io.github.tarcio4almeida.clientes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.github.tarcio4almeida.clientes.service.UsuarioService;

@EnableWebSecurity // como ela já tem dentro dela a anotation configuration, logo aqui nao é preciso
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(usuarioService)
            .passwordEncoder(passwordEncoder());
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .cors()
        .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // o controle de ssessao é feito pelo token

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}