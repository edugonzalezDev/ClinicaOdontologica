package com.example.ClinicaOdontologicaSpringMVC.service;

import com.example.ClinicaOdontologicaSpringMVC.exception.BadRequestException;
import com.example.ClinicaOdontologicaSpringMVC.repository.OdontologoRepository;
import com.example.ClinicaOdontologicaSpringMVC.entity.Odontologo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {
    @Autowired
    private OdontologoRepository odontologoRepository;

    public Odontologo guardarOdontologo(Odontologo odontologo){
        return odontologoRepository.save(odontologo);
    }
    public Optional<Odontologo> buscarPorId(Integer id){
        return odontologoRepository.findById(id);
    }
    public Optional<Odontologo> buscarPorMatricula(String matricula){
     return odontologoRepository.findByMatricula(matricula);
    }
    public List<Odontologo> listarOdontologos() throws BadRequestException {
        List<Odontologo> odontologos = odontologoRepository.findAll();
        if (odontologos.isEmpty()) {
            throw new BadRequestException("No se encontraron odontólogos registrados");
        }
        return odontologos;
    }
    public void eliminarOdontologo (Integer id) {
        odontologoRepository.deleteById(id);
    }
    public void actualizarOdontologo (Odontologo odontologo) {
        odontologoRepository.save(odontologo);
    }

}