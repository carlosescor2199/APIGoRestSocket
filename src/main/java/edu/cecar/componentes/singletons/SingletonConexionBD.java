/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cecar.componentes.singletons;


import edu.cecar.componentes.baseDatos.ConectarMySQL;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Singleton que devuelve la conexion a una Base de datos
 */
public class SingletonConexionBD {
    
    private static Connection connection;
    
    public static Connection getInstance() {
        
        if (connection == null) {
            
            try {
                Properties properties = new Properties();

                properties.load(
                        new FileInputStream
                        ("recursos/Conexion.properties"));

                connection = new
                        ConectarMySQL(properties.getProperty("host"),
                            properties.getProperty("baseDatos"),properties.getProperty("puerto"),
                            properties.getProperty("usuario"),
                    properties.getProperty("password"))
                    .getConnection();

            } catch (Exception ex) {
                Logger.getLogger(SingletonConexionBD.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
        }
        
        return connection;
    }

    
}
