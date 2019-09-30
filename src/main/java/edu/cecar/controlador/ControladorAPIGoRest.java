package edu.cecar.controlador;

import edu.cecar.modelo.Comment;
import edu.cecar.modelo.Post;
import edu.cecar.modelo.User;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;

import sun.applet.AppletResourceLoader;
import us.monoid.json.JSONArray;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;
import us.monoid.web.Resty;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class ControladorAPIGoRest {

    public static ArrayList<User> users;
    public static ArrayList<Post> posts;
    public static ArrayList<Comment> comments;
    
    static {
        try {
            users = obtenerUsers();
            posts = obtenerPost();
            comments = obtenerComment();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException ex1) {
            ex1.printStackTrace();
        } catch (ParseException ex) {
            Logger.getLogger(ControladorAPIGoRest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private static ArrayList<User> obtenerUsers() throws JSONException, IOException, ParseException {
        ArrayList<User> users = new ArrayList<>();
        User user;
        Image img;
        for (int j = 1; j <= 20; j++) {
            JSONObject jsonRoot = new Resty().json("https://gorest.co.in/public-api/users?page=" + j + "_format=json&access-token=UJ2oZtW8RZ9P92HH8BALVsbtxIEXZxW1flIq").object();

            JSONArray jsonArrayResult = jsonRoot.getJSONArray("result");

            for (int i = 0; i < jsonArrayResult.length(); i++) {

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
                java.util.Date date = sdf.parse(jsonArrayResult.getJSONObject(i).get("dob").toString());
                Date dob = new Date(date.getTime());
                
                user = new User(Integer.parseInt((String) jsonArrayResult.getJSONObject(i).get("id")), jsonArrayResult.getJSONObject(i).get("first_name").toString(),
                        jsonArrayResult.getJSONObject(i).get("last_name").toString(), jsonArrayResult.getJSONObject(i).get("gender").toString(), dob,
                        jsonArrayResult.getJSONObject(i).get("email").toString(), jsonArrayResult.getJSONObject(i).get("phone").toString(), jsonArrayResult.getJSONObject(i).get("website").toString(),
                        jsonArrayResult.getJSONObject(i).get("address").toString(), jsonArrayResult.getJSONObject(i).get("status").toString(), jsonArrayResult.getJSONObject(i).
                        getJSONObject("_links").getJSONObject("avatar").get("href").toString());
                users.add(user);
            }
        }
        System.out.println("Usuarios Cargados en memoria principal");
        return users;
    }

    private static ArrayList<Post> obtenerPost() throws IOException, JSONException {
        ArrayList<Post> posts = new ArrayList<>();
        Post post;

        for (int j = 1; j <= 20; j++) {
            JSONObject jsonRoot = new Resty().json("https://gorest.co.in/public-api/posts?page=" + j + "_format=json&access-token=UJ2oZtW8RZ9P92HH8BALVsbtxIEXZxW1flIq").object();

            JSONArray jsonArrayResult = jsonRoot.getJSONArray("result");

            for (int i = 0; i < jsonArrayResult.length(); i++) {
                post = new Post(Integer.parseInt(jsonArrayResult.getJSONObject(i).get("id").toString()), Integer.parseInt(jsonArrayResult.getJSONObject(i).get("user_id").toString()),
                        jsonArrayResult.getJSONObject(i).get("title").toString(), jsonArrayResult.getJSONObject(i).get("body").toString());
                posts.add(post);
            }
        }
        System.out.println("Post cargados en memoria principal");
        return posts;
    }

    private static ArrayList<Comment> obtenerComment() throws IOException, JSONException {
        ArrayList<Comment> comments = new ArrayList<>();
        Comment comment;

        for (int j = 1; j <= 20; j++) {
            JSONObject jsonRoot = new Resty().json("https://gorest.co.in/public-api/comments?page=" + j + "_format=json&access-token=UJ2oZtW8RZ9P92HH8BALVsbtxIEXZxW1flIq").object();

            JSONArray jsonArrayResult = jsonRoot.getJSONArray("result");

            for (int i = 0; i < jsonArrayResult.length(); i++) {
                comment = new Comment(Integer.parseInt(jsonArrayResult.getJSONObject(i).get("id").toString()),
                        Integer.parseInt(jsonArrayResult.getJSONObject(i).get("post_id").toString()), jsonArrayResult.getJSONObject(i).get("name").toString(),
                        jsonArrayResult.getJSONObject(i).get("email").toString(), jsonArrayResult.getJSONObject(i).get("body").toString());
                comments.add(comment);
            }
        }
        System.out.println("Comments cargados en memoria principal");
        return comments;
    }
}
