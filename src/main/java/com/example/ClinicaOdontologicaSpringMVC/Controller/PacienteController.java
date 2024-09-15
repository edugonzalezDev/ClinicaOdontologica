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

    @GetMapping("/buscar")
    public ResponseEntity<Optional<Paciente>> buscarPorCorreo(@RequestParam("email") String email) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado=pacienteService.buscarPorCorreo(email);
        if (pacienteBuscado.isPresent()) {
            return ResponseEntity.ok(pacienteBuscado);
        } else {
            throw new ResourceNotFoundException("Paciente no encontrado");
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Paciente>> listarTodos() throws ResourceNotFoundException {
        List<Paciente> pacientes = pacienteService.listarPacientes();
        if (pacientes.isEmpty()){
            throw new ResourceNotFoundException("No se encontraron pacientes registrados");
        }
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/buscar/{id}")
    public   ResponseEntity<Optional<Paciente>> buscarPorId (@PathVariable Integer id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPorId(id);
        if(pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado);
        }else{
            throw new ResourceNotFoundException("Paciente no encontrado");
        }
    }

    @PostMapping
    public ResponseEntity<Paciente> guardarPaciente(@RequestBody Paciente paciente) throws BadRequestException {
        Optional<Paciente> pacienteAEliminar = pacienteService.buscarPorCorreo(paciente.getCorreo());
        boolean existeCedula = pacienteService.validarCedula(paciente.getCedula());

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
        if (existeCedula){
            throw new BadRequestException("Ya existe un paciente con la cedula proporcionada");
        }
        if (pacienteAEliminar.isPresent()){
            throw new BadRequestException("Ya existe un paciente con el correo proporcionado");
        }
        if (paciente.getDomicilio().getCalle() == null || paciente.getDomicilio().getCalle().isEmpty()) {
            throw new BadRequestException("La calle del domicilio no puede estar vacía");
        }
        if (paciente.getDomicilio().getNumero() == null || paciente.getDomicilio().getNumero().toString().isEmpty()) {
            throw new BadRequestException("El número del domicilio no puede estar vacío");
        }
        if (paciente.getDomicilio().getProvincia() == null || paciente.getDomicilio().getProvincia().isEmpty()) {
            throw new BadRequestException("La provincia del domicilio no puede estar vacía");
        }
        if (paciente.getDomicilio().getLocalidad() == null || paciente.getDomicilio().getLocalidad().isEmpty()) {
            throw new BadRequestException("La localidad del domicilio no puede estar vacía");
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