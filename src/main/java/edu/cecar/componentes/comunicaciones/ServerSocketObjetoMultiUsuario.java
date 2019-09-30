/*
 * Clase: ServerSocketObjeto
 *
 * Version: 1.0
 *
 * Fecha: 2005-04-26
 *
 * Autor: Ing. Jhon Jaime Mendez
 *
 * Copyrigth: JAsoft
 */



package edu.cecar.componentes.comunicaciones;

import java.io.*;
import java.net.*;


/**
 * Esta clase configura la comunicacion a traves de Socket Servidor 
 */

final public class ServerSocketObjetoMultiUsuario {

    private ServerSocket serverSocket;
    private Socket socket;
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;



    public ServerSocketObjetoMultiUsuario(int puerto) {

            try {

                //** Se instancia el servidor  
                serverSocket = new ServerSocket(puerto);  

            } catch (Exception e)   {
                
                    System.out.println("Error General"+e);

            }

    } 

    public void esperarConexion() throws Exception {

            //** Se recibe la peticion en un socket     
            socket = serverSocket.accept();


            //** Se crea un canal de salida hacia el servidor
            salida = new ObjectOutputStream(socket.getOutputStream()); 

            //**Se crea un canal de entrada hacia el cliente
            entrada = new ObjectInputStream(socket.getInputStream());

    }


    public Socket getSocket() {

            return socket;
    }


    public ObjectInputStream getEntrada() {

            return entrada;
    }  

    public ObjectOutputStream getSalida() {

            return salida;
    }

}	



