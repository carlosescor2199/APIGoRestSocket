/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cecar.controlador;

import edu.cecar.modelo.User;

import javax.imageio.ImageIO;

import static edu.cecar.componentes.singletons.SingletonConexionBD.getInstance;
import edu.cecar.modelo.Comment;
import edu.cecar.modelo.Post;
import edu.cecar.modelo.objetos.ObjetoPostComment;
import javafx.geometry.Pos;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author carli
 */
public class ControladorBD {
    
    public static boolean consultarAdmin(String admin, String password){
        try {
            PreparedStatement ps = getInstance().prepareStatement("SELECT * FROM admin where Usuario = ? and Contraseña = ?;");
            ps.setString(1, admin);
            ps.setString(2, password);
            ResultSet r = ps.executeQuery();
            if(r.next()){
                System.out.println(r.getString(1)+" "+r.getString(2));
                return true;
            }                 
        } catch (SQLException ex) {
            System.err.println("Error al consultar en la base de datos - Descripción del error: " + ex.getMessage());
        }
        return false;
    }
    
    public static ArrayList<User> consultarUsers(){
        ArrayList<User> users = new ArrayList<User>();
        try { 
            User user = null;
            PreparedStatement ps = getInstance().prepareStatement("SELECT * FROM users;");
            ResultSet r = ps.executeQuery();
            while(r.next()){
               user = new User(r.getInt(1), r.getString(2), r.getString(3), r.getString(4),
                       r.getDate(5), r.getString(6), r.getString(7), r.getString(8), r.getString(9), r.getString(10),
                       r.getString(11));
               users.add(user);
            }                 
        } catch (SQLException ex) {
            System.err.println("Error al consultar en la base de datos - Descripción del error: " + ex.getMessage());
        }
        return users;
    }

    public static User consultarUser(int id){
        User user = null;
        try { 
            PreparedStatement ps = getInstance().prepareStatement("SELECT * FROM users where iduser = ?;");
            ps.setInt(1, id);
            ResultSet r = ps.executeQuery();
            if(r.next()){
               user = new User(r.getInt(1), r.getString(2), r.getString(3), r.getString(4),
                       r.getDate(5), r.getString(6), r.getString(7), r.getString(8), r.getString(9), r.getString(10),
                       r.getString(11));
            }                 
        } catch (SQLException ex) {
            System.err.println("Error al consultar en la base de datos - Descripción del error: " + ex.getMessage());
        }
        return user;
    }
    
    public static ArrayList<Post> consultarPosts(int id){
        ArrayList<Post> posts = new ArrayList<>();
        try { 
            Post post = null;
            PreparedStatement ps = getInstance().prepareStatement("SELECT * FROM posts where iduser = ?;");
            ps.setInt(1, id);
            ResultSet r = ps.executeQuery();
            while(r.next()){
               post = new Post(r.getInt(1), r.getInt(2), r.getString(3), r.getString(4));
               posts.add(post);
            }                 
        } catch (SQLException ex) {
            System.err.println("Error al consultar en la base de datos - Descripción del error: " + ex.getMessage());
        }
        return posts;
    }

    public static ArrayList<Comment> consultarComments(int id){
        ArrayList<Comment> comments = new ArrayList<>();
        try {
            Comment comment = null;
            PreparedStatement ps = getInstance().prepareStatement("SELECT * FROM comments where idpost = ?;");
            ps.setInt(1, id);
            ResultSet r = ps.executeQuery();
            while(r.next()){
                comment = new Comment(r.getInt(1), r.getInt(2), r.getString(3),
                        r.getString(4), r.getString(5));
                comments.add(comment);
            }
        } catch (SQLException ex) {
            System.err.println("Error al consultar en la base de datos - Descripción del error: " + ex.getMessage());
        }
        return comments;
    }

    public static ObjetoPostComment getObjetoPostComment(int id){
        User user = consultarUser(id);
        ArrayList<Post> posts = consultarPosts(id);
        ArrayList<Comment> comments = new ArrayList<>();
        posts.forEach(post -> {
            comments.addAll(consultarComments(post.getIdPost()));
        });

        ObjetoPostComment objetoPostComment = new ObjetoPostComment(user,posts,comments);
        return objetoPostComment;
    }

    public static void guardarUser(User user) throws SQLException, IOException {

        
        PreparedStatement ps = getInstance().prepareStatement("call apigorest.AlmacenarUsers(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
        ps.setInt(1, user.getId());
        ps.setString(2, user.getFirstName());
        ps.setString(3, user.getLastName());
        ps.setString(4, user.getGender());
        ps.setDate(5, user.getBirthdate());
        ps.setString(6, user.getEmail());
        ps.setString(7, user.getPhone());
        ps.setString(8, user.getWebsite());
        ps.setString(9, user.getAddress());
        ps.setString(10, user.getStatus());
        ps.setString(11, user.getAvatar());
        ps.execute();
    }


    public static void guardarPost(Post post) throws SQLException {

        PreparedStatement ps = getInstance().prepareStatement("call apigorest.AlmacenarPost(?, ?, ?, ?);");
        ps.setInt(1, post.getIdPost());
        ps.setString(2, post.getTitle());
        ps.setString(3, post.getBody());
        ps.setInt(4, post.getIdUser());
        ps.execute();
    }
    
    public static void guardarComments(Comment comment) throws SQLException {

        PreparedStatement ps = getInstance().prepareStatement("call apigorest.AlmacenarComments(?, ?, ?, ?, ?);");
        ps.setInt(1, comment.getIdComment());
        ps.setInt(2, comment.getIdPost());
        ps.setString(3, comment.getUserName());
        ps.setString(4, comment.getEmail());
        ps.setString(5, comment.getBody());
        ps.execute();
    }
}
