package com.example.ClinicaOdontologicaSpringMVC;

import com.example.ClinicaOdontologicaSpringMVC.Entity.Odontologo;
import com.example.ClinicaOdontologicaSpringMVC.Service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class OdontologoIntegracionTest {
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private MockMvc mockMvc;

    public void cargarOdontologo() {
        Odontologo odontologo = Odontologo.builder()
                .nombre("Julian")
                .apellido("Espinoza")
                .matricula("1234")
                .build();
        Odontologo odontologoGuardado= odontologoService.guardarOdontologo(odontologo);

    }


}
