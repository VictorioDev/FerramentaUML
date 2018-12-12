package com.aula.victoriozansavio.umlp5.library;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Submission implements Serializable {

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("author")
    @Expose
    private User author;

    @SerializedName("exercise")
    @Expose
    private Exercise exercise;

    @SerializedName("json")
    @Expose
    private String json;

    @SerializedName("date")
    @Expose
    private String date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}