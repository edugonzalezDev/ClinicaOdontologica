package com.example.ClinicaOdontologicaSpringMVC.Security;

import com.example.ClinicaOdontologicaSpringMVC.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder; // Ajustado para usar PasswordEncoder genérico
    @Bean //PROVEEDOR DE AUTENTICACIÓN DAO
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(usuarioService);
        return provider;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests((authz) -> authz
//                            .requestMatchers("/post_paciente.html").hasRole("USER")
                            .requestMatchers("/h2-console/**").permitAll() // Permitir acceso a la consola H2
                            .requestMatchers(antMatcher(HttpMethod.POST, "/paciente")).hasRole("ADMIN")
        //                        .requestMatchers("/**").permitAll()
                            .anyRequest().authenticated()
            )
//            .headers(headers -> headers
//                    .frameOptions(frameOptions -> frameOptions.disable()) // Deshabilitar restricciones de frame
//            )
            .formLogin(withDefaults())
            .logout(withDefaults());

        return http.build();
    }


}
