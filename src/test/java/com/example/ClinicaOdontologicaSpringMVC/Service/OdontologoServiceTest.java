package com.example.ClinicaOdontologicaSpringMVC.Service;

import com.example.ClinicaOdontologicaSpringMVC.Entity.Domicilio;
import com.example.ClinicaOdontologicaSpringMVC.Entity.Odontologo;
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
public class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    public void guardarOdontologo(){
        Odontologo odontologo = Odontologo.builder()
                .nombre("Julian")
                .apellido("Espinoza")
                .matricula("1234")
                .build();
        Odontologo odontologoGuardado= odontologoService.guardarOdontologo(odontologo);
        assertEquals(1,odontologoGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarOdontologoPorId(){
        Integer id=1;
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarPorId(id);
        assertNotNull(odontologoBuscado.get());
    }

    @Test
    @Order(3)
    public void buscarOdontologoPorMatricula(){
        String matricula="1234";
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarPorMatricula(matricula);
        assertNotNull(odontologoBuscado.get());
    }

    @Test
    @Order(4)
    public void actualizarOdontologo(){
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarPorId(1);
        if(odontologoBuscado.isPresent()){
            odontologoBuscado.get().setApellido("Herrera");
        }
        odontologoService.actualizarOdontologo(odontologoBuscado.get());
        assertEquals("Herrera",odontologoBuscado.get().getApellido());
    }

    @Test
    @Order(5)
    public void listarTodos() throws BadRequestException {
        List<Odontologo> odontologo= odontologoService.listarOdontologos();
        assertEquals(1,odontologo.size());
    }

    @Test
    @Order(6)
    public void eliminaOdontologo(){
        odontologoService.eliminarOdontologo(1);
        Optional<Odontologo> odontologoEliminado= odontologoService.buscarPorId(1);
        assertFalse(odontologoEliminado.isPresent());
    }
}
