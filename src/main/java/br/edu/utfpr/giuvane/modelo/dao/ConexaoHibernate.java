/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.giuvane.modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.HibernateException;

/**
 *
 * @author Giuvane
 */
public class ConexaoHibernate {
    
    private static EntityManagerFactory factory;
    private volatile static EntityManager  manager;
    
    //public static Connection connection = null; // manages connection
 
    private ConexaoHibernate() { }

    public static EntityManager getInstance() {
        if (manager == null) {
            synchronized (ConexaoHibernate.class) {
                if (manager == null) {

                    try {
                        factory = Persistence.createEntityManagerFactory("FinanceiroUTFPR");
                        manager = factory.createEntityManager ();
                    } catch(HibernateException he)
                    {
                        System.err.println(he.getMessage());
                    }

                }
            }
        }
        return manager;
    }  
}
