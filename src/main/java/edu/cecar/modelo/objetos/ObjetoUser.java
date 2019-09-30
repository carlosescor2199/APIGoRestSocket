package edu.cecar.modelo.objetos;

import edu.cecar.modelo.User;
import java.io.Serializable;
import java.util.ArrayList;

public class ObjetoUser extends ObjetoOpcion implements Serializable  {

        private static final long serialVersionUID = 1420672609912364060L;
        private ArrayList<User> users;
        private User user;

        public ObjetoUser(String opcion){
            super(opcion);
        }

        public ObjetoUser(ArrayList<User> users) {
            this.users = users;
        }

        public ObjetoUser(User user) {
            this.user = user;
        }

        public ArrayList<User> getUsers() {
            return users;
        }

        public User getUser(){
            return user;
        }
}
