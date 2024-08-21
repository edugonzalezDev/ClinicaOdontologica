package com.example.ClinicaOdontologicaSpringMVC.Controller;

import com.example.ClinicaOdontologicaSpringMVC.Model.Odontologo;
import com.example.ClinicaOdontologicaSpringMVC.Service.OdontologoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {
    private OdontologoService odontologoService;

    public OdontologoController() {
        odontologoService = new OdontologoService();
    }
    @PostMapping
    public Odontologo guardarOdontologo(@RequestBody Odontologo odontologo){
        return odontologoService.guardarOdontologo(odontologo);
    }
    @GetMapping("/buscar")
    public Odontologo buscarPorID(Model model, @RequestParam("id") Integer id) {
        return odontologoService.buscarPorID(id);
    }
    @DeleteMapping("/eliminar/{id}")
    public void eliminarOdontologo (@PathVariable Integer id) {
        odontologoService.eliminarOdontologo(id);
    }
}
