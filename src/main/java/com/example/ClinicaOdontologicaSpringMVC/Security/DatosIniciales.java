package com.example.ClinicaOdontologicaSpringMVC.Security;

import com.example.ClinicaOdontologicaSpringMVC.entity.Usuario;
import com.example.ClinicaOdontologicaSpringMVC.entity.UsuarioRole;
import com.example.ClinicaOdontologicaSpringMVC.repository.UsuarioRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatosIniciales implements ApplicationRunner {
    private static final Logger logger = Logger.getLogger(DatosIniciales.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;  // Usamos PasswordEncoder en lugar de BCryptPasswordEncoder

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String passSinCifrar = "asd123";
        String passCifrado = bCryptPasswordEncoder.encode(passSinCifrar);

        // Verificar si el usuario ya existe antes de guardarlo
        if (usuarioRepository.findByEmail("admin@admin.com").isEmpty()) {
            Usuario usuarioAInsertar = new Usuario("jorgito", "jorgitodh", "admin@admin.com", passCifrado, UsuarioRole.ROLE_ADMIN);
            usuarioRepository.save(usuarioAInsertar);
            logger.info("Usuario 'admin@admin.com' cargado con éxito");
        } else {
            logger.info("Usuario 'admin@admin.com' ya existe, no se cargó de nuevo.");
        }
        if (usuarioRepository.findByEmail("user@user.com").isEmpty()) {
            Usuario usuarioAInsertar2 = new Usuario("usuario", "usuariodh", "user@user.com", passCifrado, UsuarioRole.ROLE_USER);
            usuarioRepository.save(usuarioAInsertar2);
            logger.info("Usuario 'user@user.com' cargado con éxito");;
        } else {
            logger.info("Usuario 'user@user.com' ya existe, no se cargó de nuevo.");
        }
    }
}
