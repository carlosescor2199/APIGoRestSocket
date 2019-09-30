package edu.cecar.modelo.Cliente;

import edu.cecar.componentes.comunicaciones.SocketObjeto;
import edu.cecar.modelo.Post;
import edu.cecar.modelo.User;
import edu.cecar.modelo.objetos.ObjetoOpcion;
import edu.cecar.modelo.objetos.ObjetoPostComment;
import edu.cecar.modelo.objetos.ObjetoUser;


import java.io.IOException;
import java.util.ArrayList;

public class ClienteObjetos {

    /* Se hace singleton lazy para solo llamar una instancia cuando se necesite*/
    private ClienteObjetos(){

    }

    public static ArrayList<User> getAllUsers(String IP, int puerto) throws IOException, ClassNotFoundException {
        //Se crea el canal de comunicación
        SocketObjeto socketCliente = new SocketObjeto(IP, puerto);

        //Se crea el objeto que se va a enviar
        ObjetoOpcion objetoOpcion = new ObjetoOpcion("Users");

        //Se solicita un servicio
        socketCliente.getSalida().writeObject(objetoOpcion);

        //Se espera el resultado
        ObjetoUser objetoUsers = (ObjetoUser) socketCliente.getEntrada().readObject();

        //Se convierte el String en una coleccion de tipoArrayList
        ArrayList<User> arrayListUsers = objetoUsers.getUsers();

        //se retorna el resultado
        return arrayListUsers;
    }

    public static ObjetoPostComment getUserPostsComments(String IP, int puerto, int id) throws IOException, ClassNotFoundException {
        //Se crea el canal de comunicación
        SocketObjeto socketCliente = new SocketObjeto(IP, puerto);

        //Se crea el objeto que se va a enviar
        ObjetoOpcion objetoOpcion = new ObjetoOpcion("UserPostComment", id);

        //Se solicita un servicio
        socketCliente.getSalida().writeObject(objetoOpcion);

        //Se espera el resultado
        ObjetoPostComment objetoPostComment = (ObjetoPostComment) socketCliente.getEntrada().readObject();

        //Se retorna el resultado
        return objetoPostComment;
    }

    public static boolean getAdmin(String IP, int puerto, String usuario, String contraseña) throws IOException, ClassNotFoundException {
        //Se crea el canal de comunicación
        SocketObjeto socketCliente = new SocketObjeto(IP, puerto);

        //Se crea el objeto que se va a enviar
        ObjetoOpcion objetoOpcion = new ObjetoOpcion("Admin", usuario, contraseña);

        //Se solicita un servicio
        socketCliente.getSalida().writeObject(objetoOpcion);

        //Se espera el resultado
        ObjetoOpcion objetoOpcionR = (ObjetoOpcion) socketCliente.getEntrada().readObject();
        //Se retorna el resultado
        return objetoOpcionR.getRespuesta();
    }

}
