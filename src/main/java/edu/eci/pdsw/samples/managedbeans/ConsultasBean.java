package edu.eci.pdsw.samples.managedbeans;

import com.google.inject.Inject;
import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.entities.Paciente;
import edu.eci.pdsw.samples.services.ExcepcionServiciosSuscripciones;
import edu.eci.pdsw.samples.services.ServiciosConsulta;
import edu.eci.pdsw.samples.services.ServiciosPaciente;

import javax.faces.bean.ManagedBean;
import java.util.List;

@ManagedBean(name = "db")

public class ConsultasBean extends  BasePageBean{

    @Inject
    ServiciosConsulta sp;

    public List<Consulta> getConsultas() throws ExcepcionServiciosSuscripciones {
        try {
            return sp.obtenerConsulta();
        } catch (ExcepcionServiciosSuscripciones ex) {
            throw ex;
        }

    }



}
