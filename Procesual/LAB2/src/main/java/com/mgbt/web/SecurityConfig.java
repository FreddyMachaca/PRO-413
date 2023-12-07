package com.mgbt.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration //Es una clase de configuracion de Spring
@EnableWebSecurity //Activar la seguridad web
public class SecurityConfig extends WebSecurityConfigurerAdapter { //La clase debe extender de la clase WebSecurityConfigurerAdapter

    @Autowired
    private UserDetailsService userDetailsService;
    //Aunque no se llame igual, esto inyectara una instancia de la clase UsuarioService, debido a que en la
    //anotación @Service posee el nombre "userDetailsService".

    @Bean //Esta anotación es para que el objeto BCrypPasswordEncoder este dentro del contenedor de Spring
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Override
    public void configure(AuthenticationManagerBuilder build) throws Exception {
        build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/editar/**", "/agregar/**", "/eliminar/**")
                .hasRole("ADMIN")
                .antMatchers("/") //El path estandar puede ser visto por cualquier rol
                .hasAnyRole("USER", "ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
                .and()
                .exceptionHandling().accessDeniedPage("/errores/403");
        //Solo los usuarios con rol ADMIN podran entrar al path de editar, agregar y eliminar.
        //** significa todos sus URL hijas, como por ejemplo http://localhost:8080/editar/5
    }
}
