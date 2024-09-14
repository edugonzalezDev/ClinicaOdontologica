package com.example.ClinicaOdontologicaSpringMVC.Service;

import com.example.ClinicaOdontologicaSpringMVC.Dto.TurnoDTO;
import com.example.ClinicaOdontologicaSpringMVC.Exception.BadRequestException;
import com.example.ClinicaOdontologicaSpringMVC.Repository.TurnoRepository;
import com.example.ClinicaOdontologicaSpringMVC.Entity.Turno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    @Autowired
    private TurnoRepository turnoRepository;

    public TurnoDTO guardarTurno(Turno turno) {
        Turno turnoGuardado = turnoRepository.save(turno);
        return turnoATurnoDTO(turnoGuardado);
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

    public List<TurnoDTO> listarTurnos() throws BadRequestException {

        List<Turno> turnos = turnoRepository.findAll();
        List<TurnoDTO> turnosDTO = new ArrayList<>();
        for (Turno turno : turnos) {
            turnosDTO.add(turnoATurnoDTO(turno));
        }

        if (turnosDTO.isEmpty()) {
            throw new BadRequestException("No se encontraron turnos registrados");
        }
        return turnosDTO;
    }

    private TurnoDTO turnoATurnoDTO(Turno turno){
        return TurnoDTO.builder()
                .id(turno.getId())
                .pacienteId(turno.getPaciente().getId())
                .odontologoId(turno.getOdontologo().getId())
                .pacienteNombreApellido(turno.getPaciente().getNombre()+" "+turno.getPaciente().getApellido())
                .odontologoId(turno.getOdontologo().getId())
                .odontologoNombreApellido(turno.getOdontologo().getNombre()+" "+turno.getOdontologo().getApellido())
                .fecha(turno.getFecha())
                .build();
    }
}
