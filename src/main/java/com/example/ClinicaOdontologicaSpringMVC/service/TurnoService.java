package com.example.ClinicaOdontologicaSpringMVC.service;

import com.example.ClinicaOdontologicaSpringMVC.repository.TurnoRepository;
import com.example.ClinicaOdontologicaSpringMVC.entity.Turno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    @Autowired
    private TurnoRepository turnoRepository;

    public Turno guardarTurno(Turno turno) {
        return turnoRepository.save(turno);
    }
    public Optional<Turno> buscarPorId(Integer id) {
        return turnoRepository.findById(id);
    }
    public Turno actualizarTurno(Turno turno) {
        return turnoRepository.save(turno);
    }
    public void eliminarTurno (Integer id) {
        turnoRepository.deleteById(id);
    }
    public List<Turno> listarTurnos(){
        return turnoRepository.findAll();
    }
}
