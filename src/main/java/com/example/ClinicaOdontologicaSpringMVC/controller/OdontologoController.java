package com.example.ClinicaOdontologicaSpringMVC.Controller;

import com.example.ClinicaOdontologicaSpringMVC.Entity.Odontologo;
import com.example.ClinicaOdontologicaSpringMVC.Exception.BadRequestException;
import com.example.ClinicaOdontologicaSpringMVC.Exception.ResourceNotFoundException;
import com.example.ClinicaOdontologicaSpringMVC.Service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {
    @Autowired
    private OdontologoService odontologoService;

    @GetMapping("/todos")
    public ResponseEntity<List<Odontologo>> listarTodos() throws ResourceNotFoundException {
        List<Odontologo> odontologos = odontologoService.listarOdontologos();
        if (odontologos.isEmpty()){
            throw new ResourceNotFoundException("No se encontraron odontologos registrados");
        }
        return ResponseEntity.ok(odontologos);
    }

    @PostMapping
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody Odontologo odontologo) throws BadRequestException {
        if (odontologo.getNombre() == null || odontologo.getApellido() == null) {
            throw new BadRequestException("El nombre o apellido del odontólogo no pueden ser nulos");
        }
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Optional<Odontologo>> buscarPorId(@PathVariable Integer id) throws BadRequestException, ResourceNotFoundException {
        // Verificar que el ID no sea nulo o negativo
        if (id == null || id <= 0) {
            throw new BadRequestException("El ID proporcionado no es válido. Debe ser un número positivo.");
        }

        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(id);

        // Lanzar excepción si el odontólogo no fue encontrado
        if (odontologoBuscado.isPresent()) {
            return ResponseEntity.ok(odontologoBuscado);
        } else {
            throw new ResourceNotFoundException("Odontólogo no encontrado con el ID: " + id);
        }
    }
    @GetMapping("/buscar")
    public ResponseEntity<Optional<Odontologo>> buscarPorMatricula(@RequestParam("matricula") String matricula) {
        return ResponseEntity.ok(odontologoService.buscarPorMatricula(matricula));
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarOdontologo (@PathVariable Integer id) {
        odontologoService.eliminarOdontologo(id);
    }

    @PutMapping("/actualizar")
    public Odontologo actualizarOdontologo (@RequestBody Odontologo odontologo) throws BadRequestException {
        if (odontologo.getId() == null) {
            throw new BadRequestException("El ID del odontologo no puede ser nulo");
        }
        // Otras validaciones, como nombres, apellidos, etc.
        if (odontologo.getNombre() == null || odontologo.getNombre().isEmpty()) {
            System.out.println("El nombre del odontologo no puede estar vacío");
            throw new BadRequestException("El nombre del odontologo no puede estar vacío");

        }
        if (odontologo.getApellido() == null || odontologo.getApellido().isEmpty()) {
            throw new BadRequestException("El apellido del odontologo no puede estar vacío");
        }
        if (odontologo.getMatricula() == null || odontologo.getMatricula().isEmpty()) {
            throw new BadRequestException("La cedula del odontologo no puede estar vacío");
        }

        return odontologoService.actualizarOdontologo(odontologo);
    }
}
