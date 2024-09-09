package com.example.ClinicaOdontologicaSpringMVC.controller;

import com.example.ClinicaOdontologicaSpringMVC.entity.Odontologo;
import com.example.ClinicaOdontologicaSpringMVC.entity.Paciente;
import com.example.ClinicaOdontologicaSpringMVC.exception.ResourceNotFoundException;
import com.example.ClinicaOdontologicaSpringMVC.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

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
    public ResponseEntity<List<Paciente>> listarTodos(){
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
    public Paciente guardarPaciente(@RequestBody Paciente paciente){
        return pacienteService.guardarPaciente(paciente);
    }
    @DeleteMapping("/eliminar")
    public void eliminarPaciente(@RequestParam Integer id){
        pacienteService.eliminarPaciente(id);
    }
    @PutMapping("/actualizar")
    public Paciente actualizarPaciente (@RequestBody Paciente paciente){
        return pacienteService.actualizarPaciente(paciente);
    }

}