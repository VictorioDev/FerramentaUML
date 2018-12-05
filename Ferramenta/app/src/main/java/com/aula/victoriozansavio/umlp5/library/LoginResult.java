package com.aula.victoriozansavio.umlp5.library;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Victorio Zansavio on 05/12/2018.
 */
public class LoginResult {

    @SerializedName("auth")
    @Expose
    private Boolean auth;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("level")
    @Expose
    private Long level;
    @SerializedName("id")
    @Expose
    private String id;

    public Boolean getAuth() {
        return auth;
    }

    public void setAuth(Boolean auth) {
        this.auth = auth;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}