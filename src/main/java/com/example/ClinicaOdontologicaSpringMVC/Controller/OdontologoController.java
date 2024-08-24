package com.example.ClinicaOdontologicaSpringMVC.Controller;

import com.example.ClinicaOdontologicaSpringMVC.Model.Odontologo;
import com.example.ClinicaOdontologicaSpringMVC.Service.OdontologoService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {
    private OdontologoService odontologoService;

    public OdontologoController() {
        odontologoService = new OdontologoService();
    }

    @GetMapping("/todos")
    public List<Odontologo> listarTodos(){
        return odontologoService.listarOdontologos();
    }

    @PostMapping
    public Odontologo guardarOdontologo(@RequestBody Odontologo odontologo){
        return odontologoService.guardarOdontologo(odontologo);
    }

    @GetMapping("/buscar/{id}")
    public Odontologo buscarPorId(@PathVariable Integer id) {
        return odontologoService.buscarPorId(id);
    }
    @GetMapping("/buscar")
    public Odontologo buscarPorMatricula(@RequestParam("matricula") String matricula) {
        return odontologoService.buscarPorMatricula(matricula);
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
