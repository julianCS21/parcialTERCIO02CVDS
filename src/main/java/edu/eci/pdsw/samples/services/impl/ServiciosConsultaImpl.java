package edu.eci.pdsw.samples.services.impl;

import com.google.inject.Inject;
import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.persistence.DaoConsulta;
import edu.eci.pdsw.samples.persistence.DaoPaciente;
import edu.eci.pdsw.samples.persistence.PersistenceException;
import edu.eci.pdsw.samples.services.ExcepcionServiciosSuscripciones;
import edu.eci.pdsw.samples.services.ServiciosConsulta;

import java.util.List;

public class ServiciosConsultaImpl implements ServiciosConsulta {

    @Inject
    private DaoConsulta daoConsulta;

    @Override
    public List<Consulta> obtenerConsulta() throws ExcepcionServiciosSuscripciones {
        try {
            return daoConsulta.loadAll();
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosSuscripciones("Error al realizar la consulta:"+ex.getLocalizedMessage(), ex);
        }
    }
}
