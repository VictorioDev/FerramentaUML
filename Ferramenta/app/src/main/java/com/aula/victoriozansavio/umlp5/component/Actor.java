package com.aula.victoriozansavio.umlp5.component;

import android.graphics.Color;
import android.util.Log;

import com.google.gson.annotations.Expose;

import processing.core.PApplet;
import processing.core.PConstants;

import static java.lang.Math.pow;

/**
 * Created by Victorio Zansavio on 05/10/2018.
 */

public class Actor {

    @Expose
    private int id;

    @Expose
    private int x;

    @Expose
    private int y;

    @Expose
    private String name;


    private int offset_x;
    private int offset_y;
    private boolean locked;
    private boolean hover;
    private String color;
    private float size;
    private PApplet drawContainer;

    public Actor(int id, int x, int y, String name, PApplet drawContainer) {

        this.setId(id);
        this.setX(x);
        this.setY(y);

        this.setOffset_x(0);
        this.setOffset_y(0);

        this.setLocked(false);
        this.setHover(false);

        this.setName(name);
        this.setColor("#000000");
        this.setDrawContainer(drawContainer);
        this.setSize(30);
    }

    public Actor() {
        this.setOffset_x(0);
        this.setOffset_y(0);
        this.setLocked(false);
        this.setHover(false);
        this.setSize(30);
        this.setColor("#000000");


    }

    public void draw(int mouseX, int mouseY) {

        if( this.getName().isEmpty() ) return;

        this.setHover(this.isOnArea(mouseX, mouseY));

        getDrawContainer().fill(Color.parseColor(this.getColor()));
        getDrawContainer().noStroke();

        // Cabeça.
        getDrawContainer().ellipse(
                this.getX(),
                this.getY(),
                this.getSize() / 1.2f,
                this.getSize() / 1.2f
        );

        getDrawContainer().stroke( Color.parseColor(this.getColor()));
        getDrawContainer().strokeWeight( this.getSize() / 5 );

        // Tronco.
        getDrawContainer().line(
                this.getX(),
                this.getY(),
                this.getX(),
                ( this.getY() + this.getSize())
        );

        // Braços.
        getDrawContainer().line(
                this.getX() - this.getSize() / 2,
                this.getY() + this.getSize() - ( this.getSize() / 3 ),
                this.getX() + this.getSize() / 2,
                this.getY() + this.getSize() - ( this.getSize() / 3 )
        );

        // Perna esquerda.
        getDrawContainer().line(
                this.getX(),
                this.getY() + this.getSize(),
                this.getX() - this.getSize() / 3,
                this.getY() + this.getSize() + ( this.getSize() / 2 )
        );

        // Perna direita.
        getDrawContainer().line(
                this.getX(),
                this.getY() + this.getSize(),
                this.getX() + this.getSize() / 3,
                this.getY() + this.getSize() + ( this.getSize() / 2 )
        );

        getDrawContainer().noStroke();
        getDrawContainer().textAlign(PConstants.CENTER);
        getDrawContainer().textSize( this.getSize() / 2 );
        getDrawContainer().text(
                this.getName(),
                this.getX(),
                this.getY() + this.getSize() * 2.2f
        );

    }

    private boolean isOnArea(int mouseX, int mouseY) {
        return ( pow( mouseX - this.getX(), 2 ) + pow( mouseY - this.getY(), 2 ) ) <= ( pow( this.getSize() / 1.2, 2 ) );
    }

    public int[] getCenter() {
        int [] xy = new int[2];
        xy[0] = this.getX();
        xy[1] = this.getY();
        return xy;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOffset_x() {
        return offset_x;
    }

    public void setOffset_x(int offset_x) {
        this.offset_x = offset_x;
    }

    public int getOffset_y() {
        return offset_y;
    }

    public void setOffset_y(int offset_y) {
        this.offset_y = offset_y;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isHover() {
        return hover;
    }

    public void setHover(boolean hover) {
        this.hover = hover;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public float getSize() {
        return size * drawContainer.displayDensity;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public PApplet getDrawContainer() {
        return drawContainer;
    }

    public void setDrawContainer(PApplet drawContainer) {
        this.drawContainer = drawContainer;
    }
}
