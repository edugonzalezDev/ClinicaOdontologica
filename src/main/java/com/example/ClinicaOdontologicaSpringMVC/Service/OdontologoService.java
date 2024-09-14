package com.example.ClinicaOdontologicaSpringMVC.Service;

import com.example.ClinicaOdontologicaSpringMVC.Exception.BadRequestException;
import com.example.ClinicaOdontologicaSpringMVC.Repository.OdontologoRepository;
import com.example.ClinicaOdontologicaSpringMVC.Entity.Odontologo;
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

    public List<Odontologo> listarOdontologos(){
        return odontologoRepository.findAll();
    }

    public void eliminarOdontologo(Integer id) throws BadRequestException {
        // Lógica para verificar si el odontólogo existe
        boolean exists = false; // lógica para verificar existencia
        if (!exists) {
            throw new BadRequestException("Odontólogo con ID " + id + " no encontrado");
        }

        // Lógica para eliminar el odontólogo
    }

    public Odontologo actualizarOdontologo (Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }
}