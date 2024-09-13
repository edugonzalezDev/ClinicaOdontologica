package com.example.ClinicaOdontologicaSpringMVC.Controller;

import com.example.ClinicaOdontologicaSpringMVC.Entity.Odontologo;
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
    public ResponseEntity<List<Odontologo>> listarTodos(){
        return ResponseEntity.ok(odontologoService.listarOdontologos());
    }

    @PostMapping
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Optional<Odontologo>> buscarPorId(@PathVariable Integer id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarPorId(id);
        if(odontologoBuscado.isPresent()){
            return ResponseEntity.ok(odontologoBuscado);
        }else{
            throw new ResourceNotFoundException("Odontologo no encontrado");
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
    public void actualizarOdontologo (@RequestBody Odontologo odontologo){
        odontologoService.actualizarOdontologo(odontologo);
    }
}
