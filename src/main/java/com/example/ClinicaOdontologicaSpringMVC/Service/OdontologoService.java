package com.example.ClinicaOdontologicaSpringMVC.Service;

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
    public void eliminarOdontologo (Integer id) {
        odontologoRepository.deleteById(id);
    }
    public Odontologo actualizarOdontologo (Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }

}