package com.example.ClinicaOdontologicaSpringMVC.service;

import com.example.ClinicaOdontologicaSpringMVC.repository.DomicilioRepository;
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
