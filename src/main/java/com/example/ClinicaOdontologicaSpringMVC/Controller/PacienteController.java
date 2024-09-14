package com.example.ClinicaOdontologicaSpringMVC.Controller;

import com.example.ClinicaOdontologicaSpringMVC.Entity.Paciente;
import com.example.ClinicaOdontologicaSpringMVC.Exception.BadRequestException;
import com.example.ClinicaOdontologicaSpringMVC.Exception.ResourceNotFoundException;
import com.example.ClinicaOdontologicaSpringMVC.Service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    public PacienteController() {
        pacienteService= new PacienteService();
    }

    @GetMapping("/buscar")
    public ResponseEntity<Optional<Paciente>> buscarPorCorreo(@RequestParam("email") String email) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado=pacienteService.buscarPorCorreo(email);
        if (pacienteBuscado.isPresent()) {
            return ResponseEntity.ok(pacienteBuscado);
        }else {
            throw new ResourceNotFoundException("Paciente no encontrado");
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Paciente>> listarTodos() throws BadRequestException {
        return ResponseEntity.ok(pacienteService.listarPacientes());
    }

    @GetMapping("/buscar/{id}")
    public   ResponseEntity<Optional<Paciente>> buscarPorId (@PathVariable Integer id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPorId(id);
        if(pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado);
        }else{
            //en ese punto arroje una exception
            throw new ResourceNotFoundException("Paciente no encontrado");
        }
    }

    @PostMapping
    public ResponseEntity<Paciente> guardarPaciente(@RequestBody Paciente paciente) throws BadRequestException {
        // Validaciones antes de guardar
        if (paciente.getNombre() == null || paciente.getNombre().isEmpty()) {
            throw new BadRequestException("El nombre del paciente no puede estar vacío");
        }
        if (paciente.getApellido() == null || paciente.getApellido().isEmpty()) {
            throw new BadRequestException("El apellido del paciente no puede estar vacío");
        }
        if (paciente.getCedula() == null || paciente.getCedula().isEmpty()) {
            throw new BadRequestException("La cédula del paciente no puede estar vacía");
        }
        if (paciente.getCorreo() == null || paciente.getCorreo().isEmpty()) {
            throw new BadRequestException("El correo del paciente no puede estar vacío");
        }

        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<String> eliminarPaciente(@RequestParam Integer id) throws BadRequestException {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(id);
        if (!pacienteBuscado.isPresent()) {
            throw new BadRequestException("El paciente con el ID proporcionado no existe");
        }
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.ok("Paciente eliminado correctamente");
    }

    @PutMapping("/actualizar")
    public Paciente actualizarPaciente(@RequestBody Paciente paciente) throws BadRequestException {
        if (paciente.getId() == null) {
            throw new BadRequestException("El ID del paciente no puede ser nulo");
        }
        // Otras validaciones, como nombres, apellidos, etc.
        if (paciente.getNombre() == null || paciente.getNombre().isEmpty()) {
            System.out.println("El nombre del paciente no puede estar vacío");
            throw new BadRequestException("El nombre del paciente no puede estar vacío");

        }
        if (paciente.getApellido() == null || paciente.getApellido().isEmpty()) {
            throw new BadRequestException("El apellido del paciente no puede estar vacío");
        }
        if (paciente.getCedula() == null || paciente.getCedula().isEmpty()) {
            throw new BadRequestException("La cedula del paciente no puede estar vacío");
        }
        if (paciente.getDomicilio() == null || paciente.getDomicilio().getCalle().isEmpty() || paciente.getDomicilio().getLocalidad().isEmpty() || paciente.getDomicilio().getProvincia().isEmpty() || paciente.getDomicilio().getNumero() == null) {
            System.out.println("Los datos de Domicilio deben estar completos");
            throw new BadRequestException("Los datos de Domicilio deben estar completos");
        }

        return pacienteService.actualizarPaciente(paciente);
    }

}