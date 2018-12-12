package com.aula.victoriozansavio.umlp5.library;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Correction {

    @Expose
    private String status;

    @Expose
    @SerializedName("errors")
    private List<String> errors;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
