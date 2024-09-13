package com.example.ClinicaOdontologicaSpringMVC.Service;

import com.example.ClinicaOdontologicaSpringMVC.Exception.BadRequestException;
import com.example.ClinicaOdontologicaSpringMVC.Repository.TurnoRepository;
import com.example.ClinicaOdontologicaSpringMVC.Entity.Turno;
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
    public List<Turno> listarTurnos() throws BadRequestException {
        List<Turno> turnos = turnoRepository.findAll();
        if (turnos.isEmpty()) {
            throw new BadRequestException("No se encontraron turnos registrados");
        }
        return turnos;
    }
}
