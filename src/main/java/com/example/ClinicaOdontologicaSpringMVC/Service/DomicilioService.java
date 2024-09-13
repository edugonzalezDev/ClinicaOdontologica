package com.example.ClinicaOdontologicaSpringMVC.Service;

import com.example.ClinicaOdontologicaSpringMVC.Repository.DomicilioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DomicilioService {
    @Autowired
    private DomicilioRepository domicilioRepository;

    public void eliminarDomicilio(Integer id) {
        domicilioRepository.deleteById(id);
    }
}
