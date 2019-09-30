package edu.cecar.modelo.objetos;

import edu.cecar.modelo.Comment;
import edu.cecar.modelo.Post;
import edu.cecar.modelo.User;

import java.io.Serializable;
import java.util.ArrayList;

public class ObjetoPostComment extends ObjetoOpcion implements Serializable {
    private static final long serialVersionUID = 1420672609912364060L;
    private User user;
    private ArrayList<Post> posts;
    private ArrayList<Comment> comments;

    public ObjetoPostComment(){

    }

    public ObjetoPostComment(String opcion){
        super(opcion);
    }

    public ObjetoPostComment(User user, ArrayList<Post> posts, ArrayList<Comment> comments) {
        this.user = user;
        this.posts = posts;
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

}
