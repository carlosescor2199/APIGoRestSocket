package edu.cecar.modelo.objetos;

import java.io.Serializable;

public class ObjetoOpcion implements Serializable {

    private static final long serialVersionUID = 1420672609912364060L;
    private String opcion;
    private int idUser;
    private String usuario;
    private String contraseña;
    private boolean respuesta;

    public ObjetoOpcion(String opcion) {
        this.opcion = opcion;
    }

    public ObjetoOpcion(String opcion, int idUser) {
        this.opcion = opcion;
        this.idUser = idUser;
    }

    public ObjetoOpcion() {

    }

    public ObjetoOpcion(String opcion, String usuario, String contraseña){
        this.opcion = opcion;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public ObjetoOpcion (boolean respuesta){
        this.respuesta = respuesta;
    }


    public String getOpcion() {
        return opcion;
    }

    public int getIdUser(){
        return idUser;
    }

    public boolean getRespuesta(){
        return respuesta;
    }

}
