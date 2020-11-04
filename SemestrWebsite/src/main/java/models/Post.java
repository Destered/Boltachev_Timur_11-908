package models;

import java.io.Serializable;

public class Post implements Serializable {
    private String title;
    private String text;
    private String photo;
    private int post_id;
    private int author_id;

    @Override
    public String toString() {
        return title+"#%#"+text+"#%#"+photo;
    }
    public Post(){}

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public Post(String title, String text, String photo, int post_id, int user_id) {
        this.title = title;
        this.text = text;
        this.photo = photo;
        this.post_id = post_id;
        this.author_id = user_id;
    }

    public Post(String title, String text, String photo, int post_id) {
        this.title = title;
        this.text = text;
        this.photo = photo;
        this.post_id = post_id;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }
}
