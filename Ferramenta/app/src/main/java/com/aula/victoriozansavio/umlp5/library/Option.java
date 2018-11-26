package com.aula.victoriozansavio.umlp5.library;

/**
 * Created by Victorio Zansavio on 10/10/2018.
 */

public class Option {
    private String key;
    private boolean value;

    public Option(String key, boolean value){
        this.key = key;
        this.value = value;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
