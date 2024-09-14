package com.example.ClinicaOdontologicaSpringMVC.Service;

import com.example.ClinicaOdontologicaSpringMVC.Dto.TurnoDTO;
import com.example.ClinicaOdontologicaSpringMVC.Entity.Domicilio;
import com.example.ClinicaOdontologicaSpringMVC.Entity.Odontologo;
import com.example.ClinicaOdontologicaSpringMVC.Entity.Paciente;
import com.example.ClinicaOdontologicaSpringMVC.Entity.Turno;
import com.example.ClinicaOdontologicaSpringMVC.Exception.BadRequestException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TurnoServiceTest {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private TurnoService turnoService;

    @Test
    @Order(1)
    public void registrarTurno() {
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
        assertEquals(1,turnoGuardado.getId());
    }
    @Test
    @Order(2)
    public void buscarTurnoPorId(){
        Integer id=1;
        Optional<Turno> turnoBuscado= turnoService.buscarPorId(id);
        assertNotNull(turnoBuscado.get());
    }

    @Test
    @Order(3)
    public void actualizarTurno(){
        Optional<Turno> turnoBuscado= turnoService.buscarPorId(1);
        if(turnoBuscado.isPresent()){
            turnoBuscado.get().setFecha(LocalDate.of(2024,12,05));
        }
        turnoService.actualizarTurno(turnoBuscado.get());
        assertEquals(LocalDate.of(2024,12,05),turnoBuscado.get().getFecha());
    }

    @Test
    @Order(4)
    public void listarTodos() throws BadRequestException {
        List<TurnoDTO> turno= turnoService.listarTurnos();
        assertEquals(1,turno.size());
    }

    @Test
    @Order(5)
    public void eliminaTurno(){
        turnoService.eliminarTurno(1);
        Optional<Turno> turnoEliminado= turnoService.buscarPorId(1);
        assertFalse(turnoEliminado.isPresent());
    }

}
