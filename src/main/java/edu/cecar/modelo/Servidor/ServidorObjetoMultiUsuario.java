/*
 * Clase: Servidor
 *
 * Version: 1.0
 *
 * Fecha: 2014-10-10
 *
 * Autor: Ing. Jhon Jaime Mendez
 *
 * Copyrigth: JAsoft
 */


package edu.cecar.modelo.Servidor;

import edu.cecar.componentes.comunicaciones.ServerSocketObjetoMultiUsuario;
import edu.cecar.controlador.ControladorAPIGoRest;
import edu.cecar.controlador.ControladorBD;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servidor Marvel MultiUsario montado
 */



final public class ServidorObjetoMultiUsuario {

    public ServidorObjetoMultiUsuario(int puerto) {
        System.out.println("Cargando datos en el servidor...");
        ControladorAPIGoRest.users.forEach(user -> {

            try {
                ControladorBD.guardarUser(user);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        System.out.println("Usuarios cargados en Base de datos");
        ControladorAPIGoRest.posts.forEach(post -> {

            try {
                ControladorBD.guardarPost(post);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });
        System.out.println("Post cargados en Base de datos");
        ControladorAPIGoRest.comments.forEach(comment ->{

            try {
                ControladorBD.guardarComments(comment);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });
        System.out.println("Comments cargados en Base de datos");
        ControladorAPIGoRest.users = null;
        ControladorAPIGoRest.posts = null;
        ControladorAPIGoRest.comments = null;
        System.out.println("Datos bajados de memoria principal");

        ServerSocketObjetoMultiUsuario serverSocketObjeto = new ServerSocketObjetoMultiUsuario(puerto);

        System.out.println("Servidor APIGoRest Montado");

        //Se reciben n peticiones
        while (true) {

                try {

                    serverSocketObjeto.esperarConexion();
                    System.out.println("Ha llegado un cliente con la IP: " + serverSocketObjeto.getSocket().getInetAddress());
                    //Las conexiones para cada socket se administran sobre un hilo diferente
                    System.out.println("Hilo Creado");
                    HiloServidorObjeto hiloServidorMarvelObjetoMultiUsuario = new HiloServidorObjeto(serverSocketObjeto.getEntrada(),serverSocketObjeto.getSalida());
                    hiloServidorMarvelObjetoMultiUsuario.start();

                } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

        }
    }
    
    
}
