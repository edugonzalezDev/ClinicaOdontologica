package com.example.ClinicaOdontologicaSpringMVC.Security;

import com.example.ClinicaOdontologicaSpringMVC.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/", "/login", "/styles/**", "/js/**", "/assets/**").permitAll() // Permitir acceso a estas rutas sin autenticación
                        .requestMatchers(antMatcher(HttpMethod.POST, "/paciente")).hasRole("ADMIN")
                        .requestMatchers(antMatcher(HttpMethod.POST, "/odontologo")).hasRole("ADMIN")
                        .requestMatchers(antMatcher(HttpMethod.POST, "/turno")).hasAnyRole("ADMIN", "USER")
                        .requestMatchers(antMatcher(HttpMethod.PUT, "/paciente/actualizar")).hasRole("ADMIN")
                        .requestMatchers(antMatcher(HttpMethod.PUT, "/odontologo/actualizar")).hasRole("ADMIN")
                        .requestMatchers(antMatcher(HttpMethod.DELETE, "/paciente/eliminar")).hasRole("ADMIN")
                        .requestMatchers(antMatcher(HttpMethod.DELETE, "/odontologo/eliminar/**")).hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin(login -> login
                        .loginPage("/login.html")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(usuarioService);
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
