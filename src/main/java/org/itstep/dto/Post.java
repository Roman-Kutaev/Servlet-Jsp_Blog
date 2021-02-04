package org.itstep.dto;

import java.util.Objects;

public class Post {
    private String title;
    private String description;
    private String text;
    private String date;
    private String author;
    private String img;

    public Post(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public Post(String title, String description, String text, String date, String author) {
        this.title = title;
        this.description = description;
        this.text = text;
        this.date = date;
        this.author = author;
    }

    public Post(String title, String description, String text, String date, String author, String img) {
        this.title = title;
        this.description = description;
        this.text = text;
        this.date = date;
        this.author = author;
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;
        Post post = (Post) o;
        return title.equals(post.title) &&
                author.equals(post.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author);
    }
}
