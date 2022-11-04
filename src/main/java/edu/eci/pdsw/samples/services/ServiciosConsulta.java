package edu.eci.pdsw.samples.services;

import edu.eci.pdsw.samples.entities.Consulta;

import java.util.List;

public interface ServiciosConsulta {

    public List<Consulta> obtenerConsulta() throws ExcepcionServiciosSuscripciones;


}
