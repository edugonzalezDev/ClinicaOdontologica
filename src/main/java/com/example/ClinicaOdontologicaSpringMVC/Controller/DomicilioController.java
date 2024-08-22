package com.example.ClinicaOdontologicaSpringMVC.Controller;

import com.example.ClinicaOdontologicaSpringMVC.Service.DomicilioService;
import com.example.ClinicaOdontologicaSpringMVC.Service.OdontologoService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/domicilio")
public class DomicilioController {
    private DomicilioService domicilioService;

    public DomicilioController() {
        domicilioService = new DomicilioService();
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarDomicilio (@PathVariable Integer id) {
        domicilioService.eliminarDomicilio(id);
    }
}
