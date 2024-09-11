package com.example.ClinicaOdontologicaSpringMVC.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name="usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nombre;
    @Column
    private String username;
    @Column
    private String email;
    @Column
    private String password;
    @Enumerated(EnumType.STRING)
    private UsuarioRole usuarioRole;

    public Usuario(String nombre, String username, String email, String password, UsuarioRole usuarioRole) {
        this.nombre = nombre;
        this.username = username;
        this.email = email;
        this.password = password;
        this.usuarioRole = usuarioRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(usuarioRole.name());
        return Collections.singletonList(simpleGrantedAuthority);
    }


    public String getPassword() {
        return password;
    }


    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}