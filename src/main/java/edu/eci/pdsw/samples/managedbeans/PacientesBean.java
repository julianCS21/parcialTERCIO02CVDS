/*
 * Copyright (C) 2016 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.pdsw.samples.managedbeans;

import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.entities.Paciente;
import edu.eci.pdsw.samples.entities.TipoIdentificacion;
import edu.eci.pdsw.samples.services.ExcepcionServiciosSuscripciones;
import edu.eci.pdsw.samples.services.ServiciosPaciente;
import edu.eci.pdsw.samples.services.ServiciosPacientesFactory;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author hcadavid
 */
@ManagedBean(name = "mb")


@SessionScoped
public class PacientesBean extends BasePageBean{


    @Inject
    ServiciosPaciente sp;

    TipoIdentificacion tipoIdentificacion = TipoIdentificacion.CC;
    private int id;
    private Paciente pa;

    public Paciente getPa() {
        return pa;
    }

    public void setPa(Paciente pa) {
        this.pa = pa;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public TipoIdentificacion getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public List<Paciente> getData() throws Exception{
        try {
            return sp.consultarPacientes();
        } catch (ExcepcionServiciosSuscripciones ex) {
            
            throw ex;
        }
        
    }

    public TipoIdentificacion[] getTiposIdentificacion() {
        return TipoIdentificacion.values();
    }


    public void consultarPacientesPorId() throws ExcepcionServiciosSuscripciones {
        try {
            Paciente pa2 = sp.consultarPacientesPorId(id,tipoIdentificacion);
            setPa(pa2);


        } catch (ExcepcionServiciosSuscripciones ex) {

            throw ex;
        }

    }



    public ArrayList<Consulta> data(){
        return (ArrayList<Consulta>) pa.getConsultas();

    }
    
}
