package com.example.ClinicaOdontologicaSpringMVC.controller;

import com.example.ClinicaOdontologicaSpringMVC.entity.Odontologo;
import com.example.ClinicaOdontologicaSpringMVC.service.OdontologoService;
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
    public ResponseEntity<Optional<Odontologo>> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(odontologoService.buscarPorId(id));
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
