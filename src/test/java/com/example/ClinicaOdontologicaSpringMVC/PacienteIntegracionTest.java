package com.example.ClinicaOdontologicaSpringMVC;

import com.example.ClinicaOdontologicaSpringMVC.Entity.Domicilio;
import com.example.ClinicaOdontologicaSpringMVC.Entity.Paciente;
import com.example.ClinicaOdontologicaSpringMVC.Service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false) //deshabilita login en esta clase
public class PacienteIntegracionTest {

    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private MockMvc mockMvc;

    public void cargarPaciente() {
        Paciente paciente = Paciente.builder()
                .nombre("Eduardo")
                .apellido("Gonzalez")
                .cedula("123456")
                .fechaIngreso(LocalDate.of(2024, 9, 13))
                .domicilio(Domicilio.builder()
                        .calle("Calle de prueba")
                        .numero(1234)
                        .localidad("Salinas")
                        .provincia("Canelones")
                        .build())
                .correo("eduardo@gonzalez.com")
                .build();
        Paciente pacienteGuardado = pacienteService.guardarPaciente(paciente);
    }
}
