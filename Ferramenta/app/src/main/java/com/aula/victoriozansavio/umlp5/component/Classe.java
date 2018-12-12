package com.aula.victoriozansavio.umlp5.component;

import android.graphics.Color;

import com.google.gson.annotations.Expose;

import processing.core.PApplet;
import processing.core.PConstants;

import static java.lang.Math.pow;

/**
 * Created by Victorio Zansavio on 05/10/2018.
 */

public class Classe {

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
    private PApplet drawContainer;
    private int width;
    private int height;

    public Classe(int id, int x, int y, String name, PApplet drawContainer) {

        this.setId(id);
        this.setX(x);
        this.setY(y);

        this.setOffset_x(0);
        this.setOffset_y(0);
        this.setLocked(false);
        this.setHover(false);
        this.setName(name);
        this.setColor("#ffffcc");
        this.setDrawContainer(drawContainer);
    }

    public Classe() {
        this.setOffset_x(0);
        this.setOffset_y(0);
        this.setLocked(false);
        this.setHover(false);
        this.setColor("#ffffcc");
    }

    public void draw(int mouseX, int mouseY) {
        if( this.getName().isEmpty() ) return;
        this.setHover(this.isOnArea(mouseX, mouseY));
        getDrawContainer().fill(Color.parseColor(this.getColor()));
        getDrawContainer().stroke(Color.parseColor("#ffffff"));
        getDrawContainer().rect(this.x, this.y, this.width, this.height);

    }



    private boolean isOnArea(int mouseX, int mouseY) {
        double a = ( pow( mouseX - this.getX(), 2 ) ) / ( pow( this.getWidth() / 2, 2 ) );
        double b = ( pow( mouseY - this.getY(), 2 ) ) / ( pow( this.getHeight() / 2, 2 ) );
        return( a + b <= 1 ? true : false );
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


    public PApplet getDrawContainer() {
        return drawContainer;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setDrawContainer(PApplet drawContainer) {
        this.drawContainer = drawContainer;
    }
}
