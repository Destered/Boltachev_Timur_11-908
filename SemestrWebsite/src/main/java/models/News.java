package models;

import java.io.Serializable;

public class News implements Serializable {
    private String title;
    private String text;
    private String photo;
    private int news_id;

    public News(String title, String text, String photo, int news_id) {
        this.title = title;
        this.text = text;
        this.photo = photo;
        this.news_id = news_id;
    }

    public News() {
    }

    public int getNews_id() {
        return news_id;
    }

    public void setNews_id(int news_id) {
        this.news_id = news_id;
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

    @Override
    public String toString() {
        return title + "&" + text + "&" + photo;
    }
}
