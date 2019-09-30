package edu.cecar.test;

import edu.cecar.modelo.Servidor.ServidorObjetoMultiUsuario;

import java.io.IOException;

public class TestServidor {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new ServidorObjetoMultiUsuario(17000);
    }

}
