package com.example.ClinicaOdontologicaSpringMVC.Security;

import com.example.ClinicaOdontologicaSpringMVC.Service.UsuarioService;
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
                            .requestMatchers("/", "/login", "/styles/**", "/js/**", "/assets/**").permitAll() // Permitir acceso a estas rutas sin autenticación
                            .requestMatchers("/paciente").hasRole("ADMIN")
                            .requestMatchers(antMatcher(HttpMethod.POST, "/paciente")).hasRole("ADMIN")
                            .requestMatchers(antMatcher(HttpMethod.POST, "/odontologo")).hasRole("ADMIN")
                            .requestMatchers(antMatcher(HttpMethod.POST, "/turno")).hasAnyRole("ADMIN", "USER")
                            .requestMatchers(antMatcher(HttpMethod.PUT, "/paciente/actualizar")).hasRole("ADMIN")
                            .requestMatchers(antMatcher(HttpMethod.PUT, "/odontologo/actualizar")).hasRole("ADMIN")
                            .requestMatchers(antMatcher(HttpMethod.DELETE, "/paciente/eliminar")).hasRole("ADMIN")
                            .requestMatchers(antMatcher(HttpMethod.DELETE, "/odontologo/eliminar/**")).hasRole("ADMIN")
        //                        .requestMatchers("/**").permitAll()
                            .anyRequest().authenticated()
            )
//            .headers(headers -> headers
//                    .frameOptions(frameOptions -> frameOptions.disable()) // Deshabilitar restricciones de frame
//            )
                .formLogin(login -> login
                        .loginPage("/login.html") // Ruta del formulario de login
                        .loginProcessingUrl("/login") // Acción del formulario
                        .defaultSuccessUrl("/", true) // Redirige a /home después de login exitoso
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout") // Redirige a /login después del logout
                        .permitAll()
                );

        return http.build();
    }


}
