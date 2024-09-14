package com.example.ClinicaOdontologicaSpringMVC.Service;

import com.example.ClinicaOdontologicaSpringMVC.Entity.Domicilio;
import com.example.ClinicaOdontologicaSpringMVC.Entity.Paciente;
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
public class PacienteServiceTest {
    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    public void guardarPaciente(){
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
        assertEquals(1,pacienteGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarPacientePorId(){
        Integer id=1;
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPorId(id);
        assertNotNull(pacienteBuscado.get());
    }

    @Test
    @Order(3)
    public void buscarPacientePorCorreo(){
        String correo="eduardo@gonzalez.com";
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPorCorreo(correo);
        assertNotNull(pacienteBuscado.get());
    }

    @Test
    @Order(4)
    public void actualizarPaciente(){
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPorId(1);
        if(pacienteBuscado.isPresent()){
            pacienteBuscado.get().setApellido("Perez");
        }
        pacienteService.actualizarPaciente(pacienteBuscado.get());
        assertEquals("Perez",pacienteBuscado.get().getApellido());
    }

    @Test
    @Order(5)
    public void listarTodos() throws BadRequestException {
        List<Paciente> pacientes= pacienteService.listarPacientes();
        assertEquals(1,pacientes.size());
    }

    @Test
    @Order(6)
    public void eliminaPaciente(){
        pacienteService.eliminarPaciente(1);
        Optional<Paciente> pacienteEliminado= pacienteService.buscarPorId(1);
        assertFalse(pacienteEliminado.isPresent());
    }
}
