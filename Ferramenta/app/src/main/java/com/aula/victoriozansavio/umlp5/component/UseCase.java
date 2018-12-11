package com.aula.victoriozansavio.umlp5.component;

import android.graphics.Color;

import com.google.gson.annotations.Expose;

import processing.core.PApplet;
import processing.core.PConstants;
import static java.lang.Math.pow;

/**
 * Created by Victorio Zansavio on 05/10/2018.
 */

public class UseCase {

    @Expose
    private int  id;

    @Expose
    private int x;

    @Expose
    private int y;

    @Expose
    private String name;

    private int offset_x;
    private int offset_y;
    private boolean hover;
    private boolean locked;
    private int textSize;
    private int stroke;
    private String backgroundColor;
    private String color;
    private int width;
    private int height;
    private PApplet drawContainer;

    /*private transient int offset_x;
    private transient int offset_y;
    private transient boolean hover;
    private transient boolean locked;
    private transient int textSize;
    private transient int stroke;
    private transient String backgroundColor;
    private transient String color;
    private transient int width;
    private transient int height;
    private transient PApplet drawContainer;*/

    public UseCase(int id, int x, int y, String name, PApplet drawContainer){
        this.setId(id);
        this.setX(x);
        this.setY(y);
        this.setName(name);

        this.setOffset_x(0);
        this.setOffset_y(0);

        this.setHover(false);
        this.setLocked(false);

        this.setTextSize(15);
        this.setStroke(3);
        this.setBackgroundColor("#edff00");
        this.setColor("#000000");

        this.setWidth(120);
        this.setHeight(75);
        this.drawContainer = drawContainer;
    }

    public UseCase(){
        this.setOffset_x(0);
        this.setOffset_y(0);
        this.setHover(false);
        this.setLocked(false);
        this.setTextSize(15);
        this.setStroke(3);
        this.setBackgroundColor("#edff00");
        this.setColor("#000000");
        this.setWidth(120);
        this.setHeight(75);
    }


    public PApplet getDrawContainer() {
        return drawContainer;
    }

    public void setDrawContainer(PApplet drawContainer) {
        this.drawContainer = drawContainer;
    }

    public void draw(int mouseX, int mouseY) {

        if( this.getName().isEmpty() ) return;

        this.setHover(this.isOnArea(mouseX, mouseY));

        drawContainer.noStroke();

        if(this.isHover()) {
            drawContainer.stroke( 0, 0, 0 );
            drawContainer.strokeWeight(this.getStroke());
        }

        drawContainer.fill(Color.parseColor(this.getBackgroundColor()));
        drawContainer.ellipse(this.getX(), this.getY(), this.getWidth() * drawContainer.displayDensity, this.getHeight() * drawContainer.displayDensity );

        drawContainer.noStroke();
        drawContainer.textAlign(PConstants.CENTER);
        drawContainer.textSize( this.getTextSize() * drawContainer.displayDensity );
        drawContainer.fill( Color.parseColor(this.getColor()) );
        drawContainer.text(this.getName(), this.getX(), this.getY());
    }

    /**
     * Método para verificar se o cursor (x, y) está dentro dessa ellipse.
     * https://math.stackexchange.com/questions/76457/check-if-a-point-is-within-an-ellipse/76463
     * @return {bool}
     */
    private boolean isOnArea(int mouseX, int mouseY) {
        double a = ( pow( mouseX - this.getX(), 2 ) ) / ( pow( this.getWidth() / 2, 2 ) );
        double b = ( pow( mouseY - this.getY(), 2 ) ) / ( pow( this.getHeight() / 2, 2 ) );
        return( a + b <= 1 ? true : false );
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

    public boolean isHover() {
        return hover;
    }

    public void setHover(boolean hover) {
        this.hover = hover;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public int getStroke() {
        return stroke;
    }

    public void setStroke(int stroke) {
        this.stroke = stroke;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public int[] getCenter() {
        int [] xy = new int[2];
        xy[0] = this.getX();
        xy[1] = this.getY();
        return xy;
    }
}
