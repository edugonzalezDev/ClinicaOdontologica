package com.example.ClinicaOdontologicaSpringMVC;

import com.example.ClinicaOdontologicaSpringMVC.Dto.TurnoDTO;
import com.example.ClinicaOdontologicaSpringMVC.Entity.Domicilio;
import com.example.ClinicaOdontologicaSpringMVC.Entity.Odontologo;
import com.example.ClinicaOdontologicaSpringMVC.Entity.Paciente;
import com.example.ClinicaOdontologicaSpringMVC.Entity.Turno;
import com.example.ClinicaOdontologicaSpringMVC.Service.OdontologoService;
import com.example.ClinicaOdontologicaSpringMVC.Service.PacienteService;
import com.example.ClinicaOdontologicaSpringMVC.Service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false) //deshabilita login en esta clase
public class TurnoIntegracionTest {

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private MockMvc mockMvc;

    public void cargarTurnos(){

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

        Odontologo odontologo = Odontologo.builder()
                .nombre("Julian")
                .apellido("Espinoza")
                .matricula("1234")
                .build();
        Odontologo odontologoGuardado= odontologoService.guardarOdontologo(odontologo);

        Turno turno = Turno.builder()
                .odontologo(odontologoGuardado)
                .paciente(pacienteGuardado)
                .fecha(LocalDate.of(2024,11,25))
                .build();

        TurnoDTO turnoGuardado = turnoService.guardarTurno(turno);

    }

}
