package com.aula.victoriozansavio.umlp5.component;

import processing.core.PApplet;
import processing.core.PConstants;

/**
 * Created by Victorio Zansavio on 08/10/2018.
 */

public class Anotation {
    private int id;
    private int x;
    private int y;
    private float size;
    private String text;
    private PApplet drawContainer;


    public Anotation( int id, int x, int y, String text, PApplet drawContainer ) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.text = text;
        this.size = 18f * drawContainer.displayDensity;
        this.drawContainer = drawContainer;
    }

    public void draw() {
        if( this.text.isEmpty() ) return;
        drawContainer.noStroke();
        drawContainer.textAlign(PConstants.LEFT);
        drawContainer.textSize( this.size );
        drawContainer.text( this.text, this.x, this.y);
    }
}
