package com.aula.victoriozansavio.umlp5.component;

import android.graphics.Color;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import processing.core.PApplet;
import processing.core.PConstants;

/**
 * Created by Victorio Zansavio on 06/10/2018.
 */

public class Include {

    @Expose
    private int id;

    @Expose
    @SerializedName("de")
    private int from;

    @Expose
    @SerializedName("para")
    private int to;
    private int stroke;
    private String color;
    private int size;
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private PApplet drawContainer;

    public Include(int id, int from, int to, PApplet drawContainer) {
        this.setId(id);

        this.setFrom(from);
        this.setTo(to);

        this.setStroke(2);
        this.setSize(10);
        this.setColor("#000000");
        this.drawContainer = drawContainer;
    }

    public Include() {

        this.setStroke(2);
        this.setSize(10);
        this.setColor("#000000");

    }

    public void draw() {
        drawContainer.stroke(Color.parseColor(this.getColor()));
        drawContainer.strokeWeight( this.getStroke() * drawContainer.displayDensity);
        drawContainer.line(this.getX1(), this.getY1(), this.getX2(), this.getY2());

        drawContainer.noStroke();
        drawContainer.textAlign(PConstants.CENTER);
        drawContainer.textSize( 14  * drawContainer.displayDensity);

        drawContainer.text(
                "<< Include >>",
                this.getX1() / 2 + this.getX2() / 2,
                this.getY1() / 2 + this.getY2() / 2
        );
    }

    public void setPositions( int x1, int y1, int x2, int y2 ) {
        this.setX1(x1);
        this.setY1(y1);
        this.setX2(x2);
        this.setY2(y2);
    }

    public PApplet getDrawContainer() {
        return drawContainer;
    }

    public void setDrawContainer(PApplet drawContainer) {
        this.drawContainer = drawContainer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }
}
