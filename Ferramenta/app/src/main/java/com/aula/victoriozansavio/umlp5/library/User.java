package com.aula.victoriozansavio.umlp5.library;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Victorio Zansavio on 04/12/2018.
 */

public class User implements Serializable{


    @Expose
    @SerializedName("organization")
    private String organization;

    @Expose
    @SerializedName("name")
    private String nome;

    @Expose
    @SerializedName("_id")
    private String id;

    @Expose
    @SerializedName("email")
    private String email;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("level")
    @Expose
    private int level;


    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}


