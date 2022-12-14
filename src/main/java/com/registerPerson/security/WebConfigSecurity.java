package com.registerPerson.security;


import com.registerPerson.services.ImplementsUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private ImplementsUserDetailsService implementsUserDetailsService;


    @Override //CONFIGURAR SOLICITAÇÕES DE ACESSO POR HTTP
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable() //desativar as config padrão do spring
                .authorizeRequests() //permitir restringir acessos
                .antMatchers(HttpMethod.GET, "/").permitAll() // quauqluer usuario acessa a pagina inteira
                .antMatchers("/css/**").permitAll()
                .antMatchers(HttpMethod.GET, "/registerPerson").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and().formLogin().permitAll() //permite qualquer usuario
                .loginPage("/login")
                .defaultSuccessUrl("/registerPerson")
                .failureUrl("/login?error=true")
                .and().logout().logoutSuccessUrl("/login") // mapeia a url de logout e invalida o usuario autenticado
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }


    @Override // CRIA AUTENTICAÇÃO DO USUARIO EM BANCO DE DADOS OU EM MEMORIA
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(implementsUserDetailsService)
        .passwordEncoder((new BCryptPasswordEncoder()));


//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("alex")
//                .password("123")
//                .roles("ADMIN");
    }

    @Override // IGNORA A URL ESPECIFICA
    public void configure(WebSecurity web) throws  Exception {

        web.ignoring().antMatchers("/css/**");
    }
}
