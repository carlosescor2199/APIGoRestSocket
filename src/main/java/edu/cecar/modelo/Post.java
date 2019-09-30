package edu.cecar.modelo;

import java.io.Serializable;

public class Post implements Serializable {
    private static final long serialVersionUID = 1420672609912364060L;
    private int idPost;
    private int idUser;
    private String title;
    private String body;

    public Post(int idPost, int idUser, String title, String body) {
        this.idPost = idPost;
        this.idUser = idUser;
        this.title = title;
        this.body = body;
    }

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
