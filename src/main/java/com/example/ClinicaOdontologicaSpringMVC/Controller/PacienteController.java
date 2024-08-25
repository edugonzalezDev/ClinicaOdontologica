package com.example.ClinicaOdontologicaSpringMVC.Controller;

import com.example.ClinicaOdontologicaSpringMVC.Model.Paciente;
import com.example.ClinicaOdontologicaSpringMVC.Service.PacienteService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    private PacienteService pacienteService;

    public PacienteController() {
        pacienteService= new PacienteService();
    }
    @GetMapping("/buscar")
    public Paciente buscarPorCorreo(@RequestParam("email") String email)  {
        return pacienteService.buscarPorCorreo(email);
    }
    @GetMapping("/buscar/{id}")
    public Paciente buscarPorId (@PathVariable Integer id) {
        Paciente paciente = pacienteService.buscarPorId(id);
        return paciente;
    }
    @PostMapping
    public Paciente guardarPaciente(@RequestBody Paciente paciente){
        return pacienteService.guardarPaciente(paciente);
    }
    @DeleteMapping("/eliminar")
    public void eliminarPaciente(@RequestParam Integer id){
        pacienteService.eliminarPaciente(id);
    }
//    @PutMapping
//    public Paciente actualizar (@RequestBody Paciente paciente){
//        return pacienteService.ac
//    }

}