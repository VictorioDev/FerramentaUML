package com.aula.victoriozansavio.umlp5.component;

import android.graphics.Color;

import processing.core.PApplet;

/**
 * Created by Victorio Zansavio on 05/10/2018.
 */

public class Generalization {

    private int id;
    private int x1;
    private  int y1;
    private int x2;
    private int y2;
    private int stroke;
    private String color;
    private PApplet drawContainer;


    public Generalization(int id, int x1, int y1, int x2, int y2, PApplet drawContainer) {
        this.setId(id);
        this.setX1(x1);
        this.setY1(y1);
        this.setX2(x2);
        this.setY2(y2);
        this.setStroke(4);
        this.setColor("#000000");
        this.setStroke(4);
        this.drawContainer = drawContainer;
        this.setColor("#000000");
    }

    public void draw() {
        drawContainer.stroke(Color.parseColor(this.getColor()));
        drawContainer.strokeWeight(this.getStroke());
        drawContainer.line(this.getX1(), this.getY1(), this.getX2(), this.getY2());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public int getStroke() {
        return stroke;
    }

    public void setStroke(int stroke) {
        this.stroke = stroke;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
