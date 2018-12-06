package com.aula.victoriozansavio.umlp5.library;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class Exercise implements Serializable {

    @Expose
    private String title;

    @Expose
    private User author;

    @Expose
    private int type;

    @Expose
    private String description;

    @Expose
    private String json;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
