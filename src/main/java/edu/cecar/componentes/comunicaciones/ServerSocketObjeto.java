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

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Esta clase configura la comunicacion a traves de Socket Servidor 
 */

final public class ServerSocketObjeto {

	private ServerSocket serverSocket;
	private Socket socket;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;



	public ServerSocketObjeto(int puerto) {

		try {

			//** Se instancia el servidor  
			serverSocket = new ServerSocket(puerto);  

			//** Se recibe la peticion en un socket     
			socket = serverSocket.accept();

			//** Se crea un canal de salida hacia el servidor
			salida = new ObjectOutputStream(socket.getOutputStream()); 

			//**Se crea un canal de entrada hacia el cliente
			entrada = new ObjectInputStream(socket.getInputStream());

		} catch (Exception e)   {
			System.out.println("Error General"+e);

		}

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



