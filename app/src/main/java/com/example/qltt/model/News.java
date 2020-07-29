package com.example.qltt.model;

import java.io.Serializable;

public class News implements Serializable {
    private int id;
    private String header;
    private String image;
    private String content;
    private String link;
    private String time;
    private int views;
    private String category;

    public News(int id, String header, String image, String content, String link, String time, int views, String category) {
        this.id = id;
        this.header = header;
        this.image = image;
        this.content = content;
        this.link = link;
        this.time = time;
        this.views = views;
        this.category = category;
    }

    public News(String header, String image, String content, String link, String time, int views, String category) {
        this.header = header;
        this.image = image;
        this.content = content;
        this.link = link;
        this.time = time;
        this.views = views;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
