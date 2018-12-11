package com.aula.victoriozansavio.umlp5.component;

import com.google.gson.annotations.Expose;

import processing.core.PApplet;
import processing.core.PConstants;

/**
 * Created by Victorio Zansavio on 08/10/2018.
 */

public class Annotation {
    @Expose
    private int id;

    @Expose
    private int x;

    @Expose
    private int y;

    private float size;

    @Expose
    private String text;

    private PApplet drawContainer;


    public Annotation(int id, int x, int y, String text, PApplet drawContainer ) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.text = text;
        this.size = 18f;
        this.drawContainer = drawContainer;
    }


    public Annotation() {
        this.size = 18f;
    }

    public void draw() {
        if( this.text.isEmpty() ) return;
        drawContainer.noStroke();
        drawContainer.textAlign(PConstants.LEFT);
        drawContainer.textSize( this.size * drawContainer.displayDensity );
        drawContainer.text( this.text, this.x, this.y);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getSize() {
        return size * drawContainer.displayDensity;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public PApplet getDrawContainer() {
        return drawContainer;
    }

    public void setDrawContainer(PApplet drawContainer) {
        this.drawContainer = drawContainer;
    }
}
