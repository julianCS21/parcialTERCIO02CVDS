/*
 * Copyright (C) 2015 hcadavid
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
package edu.eci.pdsw.samples.tests;

import com.google.inject.Inject;
import edu.eci.pdsw.samples.entities.Paciente;
import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.entities.TipoIdentificacion;
import edu.eci.pdsw.samples.services.ExcepcionServiciosSuscripciones;
import edu.eci.pdsw.samples.services.ServiciosPacientesFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author hcadavid
 */
public class ServicesJUnitTest {




    public ServicesJUnitTest() {
    }

    @Before
    public void setUp() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:file:./target/db/testdb;MODE=MYSQL", "anonymous", "anonymous");
        Statement stmt = conn.createStatement();
        //stmt.execute("CREATE TABLE `PACIENTES` (\n" +
                //"  `id` int(11) NOT NULL,\n" +
                //"  `tipo_id` varchar(2),\n" +
                //"  `nombre` varchar(45) NOT NULL,\n" +
                //"  `fecha_nacimiento` date NOT NULL,\n" +
                //"  PRIMARY KEY (`id`,`tipo_id`)\n" +
                //") ENGINE=InnoDB;");
        //stmt.execute("CREATE TABLE `CONSULTAS` (\n" +
                //"  `idCONSULTAS` int(11) NOT NULL AUTO_INCREMENT,\n" +
                //"  `fecha_y_hora` datetime NOT NULL,\n" +
                //"  `resumen` text  NOT NULL,\n" +
                //"  `PACIENTES_id` int(11) NOT NULL DEFAULT '0',\n" +
                //"  `PACIENTES_tipo_id` varchar(2),\n" +
                //"  PRIMARY KEY (`idCONSULTAS`),\n" +
                //"  KEY `fk_CONSULTAS_PACIENTES1` (`PACIENTES_id`,`PACIENTES_tipo_id`),\n" +
                //"  CONSTRAINT `fk_CONSULTAS_PACIENTES1` FOREIGN KEY (`PACIENTES_id`, `PACIENTES_tipo_id`) REFERENCES `PACIENTES` (`id`, `tipo_id`) ON DELETE NO ACTION ON UPDATE NO ACTION\n" +
                //") ENGINE=InnoDB;\n");
        conn.commit();
        conn.close();


    }

    @After
    public void clearDB() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:file:./target/db/testdb;MODE=MYSQL", "anonymous", "anonymous");
        Statement stmt = conn.createStatement();
        stmt.execute("delete from CONSULTAS");
        stmt.execute("delete from PACIENTES");
        conn.commit();
        conn.close();
    }

    /**
     * Obtiene una conexion a la base de datos de prueba
     * @return
     * @throws SQLException 
     */
    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:h2:file:./target/db/testdb;MODE=MYSQL", "anonymous", "anonymous");
    }
    
    @Test
    public void DadoUnIdDeberiaConsultarelpaciente() throws SQLException, ExcepcionServiciosSuscripciones {
        //arrange
        Connection conn=getConnection();
        Statement stmt=conn.createStatement();

        stmt.execute("INSERT INTO `PACIENTES` (`id`, `tipo_id`, `nombre`, `fecha_nacimiento`) VALUES (9876,'TI','Carmenzo','1995-07-10')");
        stmt.execute("INSERT INTO `CONSULTAS` (`idCONSULTAS`, `fecha_y_hora`, `resumen`, `PACIENTES_id`, `PACIENTES_tipo_id`) VALUES (1262218,'2001-01-01 00:00:00','Gracias',9876,'TI')");

        conn.commit();
        conn.close();
	

        
        //act
        Paciente paciente = ServiciosPacientesFactory.getInstance().getTestingForumServices().consultarPacientesPorId(9876, TipoIdentificacion.TI);
        List<Paciente> pacientes = ServiciosPacientesFactory.getInstance().getTestingForumServices().consultarPacientes();
        boolean var = false;
        for (Paciente p : pacientes){
            if(paciente.getId() == p.getId()){
                var = true;
                break;

            }
        }
        //assert
        Assert.assertTrue(var);
    }



    

}
