package edu.cecar.modelo;

import java.io.Serializable;

public class Comment implements Serializable {
    private static final long serialVersionUID = 1420672609912364060L;
    private int idComment;
    private int idPost;
    private String userName;
    private String email;
    private String body;

    public Comment(int idComment, int idPost, String userName, String email, String body) {
        this.idComment = idComment;
        this.idPost = idPost;
        this.userName = userName;
        this.email = email;
        this.body = body;
    }

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
