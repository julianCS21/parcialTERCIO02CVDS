/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.services;

import edu.eci.pdsw.samples.entities.Paciente;
import edu.eci.pdsw.samples.persistence.DaoConsulta;
import edu.eci.pdsw.samples.persistence.mybatisimpl.MyBatisDAOConsulta;
import edu.eci.pdsw.samples.services.impl.ServiciosConsultaImpl;
import org.mybatis.guice.XMLMyBatisModule;
import org.mybatis.guice.datasource.helper.JdbcHelper;

import static com.google.inject.Guice.createInjector;
import com.google.inject.Injector;
import edu.eci.pdsw.samples.persistence.DaoPaciente;
import edu.eci.pdsw.samples.persistence.mybatisimpl.MyBatisDAOPaciente;
import edu.eci.pdsw.samples.services.impl.ServiciosPacienteImpl;

import javax.inject.Inject;

/**
 *
 * @author hcadavid
 */
public class ServiciosConsultasFactory {


    private static ServiciosConsultasFactory instance = new ServiciosConsultasFactory();

    private static Injector injector;
    private static Injector testingInjector;

    private ServiciosConsultasFactory() {
        injector = createInjector(new XMLMyBatisModule() {

                                      @Override
                                      protected void initialize() {
                                          install(JdbcHelper.MySQL);
                                          setClassPathResource("mybatis-config.xml");
                                          bind(ServiciosConsulta.class).to(ServiciosConsultaImpl.class);
                                          bind(DaoConsulta.class).to(MyBatisDAOConsulta.class);

                                      }

                                  }
        );

        testingInjector = createInjector(new XMLMyBatisModule() {

                                             @Override
                                             protected void initialize() {
                                                 install(JdbcHelper.MySQL);
                                                 setClassPathResource("mybatis-config-h2.xml");
                                                 bind(ServiciosConsulta.class).to(ServiciosConsultaImpl.class);
                                                 bind(DaoConsulta.class).to(MyBatisDAOConsulta.class);
                                             }

                                         }
        );

    }

    public ServiciosConsulta getForumsServices() {
        return injector.getInstance(ServiciosConsulta.class);
    }

    public ServiciosConsulta getTestingForumServices() {
        return testingInjector.getInstance(ServiciosConsulta.class);
    }

    public static ServiciosConsultasFactory getInstance() {
        return instance;
    }

    public static void main(String a[]) throws ExcepcionServiciosSuscripciones {
        System.out.println(ServiciosPacientesFactory.getInstance().getForumsServices().consultarPacientes());
    }

}
