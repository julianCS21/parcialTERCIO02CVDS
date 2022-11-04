package edu.eci.pdsw.samples.persistence;

import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.entities.Paciente;

import java.util.List;

public interface DaoConsulta {


    public List<Consulta> loadAll() throws PersistenceException;
}
