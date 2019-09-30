/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cecar.modelo.Servidor;


import edu.cecar.controlador.ControladorBD;
import edu.cecar.modelo.objetos.ObjetoOpcion;
import edu.cecar.modelo.objetos.ObjetoPostComment;
import edu.cecar.modelo.objetos.ObjetoUser;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Slave
 */
public class HiloServidorObjeto extends  Thread {
    
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;

    public HiloServidorObjeto(ObjectInputStream entrada, ObjectOutputStream salida) {

            this.entrada = entrada;
            this.salida = salida;
    }
    
    @Override
    public void run() {
       
        System.out.println("Hilo servidor APIGoRestMontado");
        
        try {
            while(true) {

                //Se espera un mensaje del cliente
                ObjetoOpcion objetoOpcion = (ObjetoOpcion) entrada.readObject();



                ObjetoOpcion resultado = null;
                if (objetoOpcion.getOpcion().equalsIgnoreCase("Admin")){
                    resultado = new ObjetoOpcion(ControladorBD.consultarAdmin(objetoOpcion.getUsuario(),objetoOpcion.getContraseña()));
                }else {
                    if (objetoOpcion.getOpcion().equalsIgnoreCase("Users")) {   //Se verifica el servicio solictado
                        if(ControladorBD.consultarUsers().isEmpty()){
                            resultado = new ObjetoUser("No se ha traido nada");
                        }else{
                            resultado = new ObjetoUser(ControladorBD.consultarUsers());
                        }
                    }else{
                        if (objetoOpcion.getOpcion().equalsIgnoreCase("UserPostComment")) {
                            resultado = new ObjetoPostComment(ControladorBD.getObjetoPostComment(objetoOpcion.getIdUser()).getUser(),
                                    ControladorBD.getObjetoPostComment(objetoOpcion.getIdUser()).getPosts(),
                                    ControladorBD.getObjetoPostComment(objetoOpcion.getIdUser()).getComments());;
                        }else{
                            resultado = new ObjetoOpcion("No se tiene el servicio solicitado");
                        }
                    }
                }


                //Se devuelve la salida del servicio al cliente
                salida.writeObject(resultado);
            }
        
        }  catch (Exception ex) {
            
            System.out.println("Conexion ha sido cerrada");
            
        }
    }
}
