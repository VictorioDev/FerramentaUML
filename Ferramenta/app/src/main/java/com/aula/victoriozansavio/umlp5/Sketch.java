package com.aula.victoriozansavio.umlp5;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.aula.victoriozansavio.umlp5.component.Actor;
import com.aula.victoriozansavio.umlp5.component.Anotation;
import com.aula.victoriozansavio.umlp5.component.Association;
import com.aula.victoriozansavio.umlp5.component.Extend;
import com.aula.victoriozansavio.umlp5.component.Generalization;
import com.aula.victoriozansavio.umlp5.component.Include;
import com.aula.victoriozansavio.umlp5.component.UseCase;
import com.aula.victoriozansavio.umlp5.library.Option;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

/**
 * Created by Victorio Zansavio on 05/10/2018.
 */

public class Sketch extends PApplet {

    // Main Variables
    private ArrayList<UseCase> tempCase = new ArrayList<>();
    private int id = 1;
    private int mode;
    private Context sketchContext;
    private ArrayList<Option> options = buildOptions();
    private int idDiagrama = 0;
    private List<UseCase> useCaseList = new ArrayList<>();
    private List<Association> associationList = new ArrayList<>();
    private List<Generalization> generalizationList = new ArrayList<>();
    private List<Actor> actorList = new ArrayList<>();
    private List<Include> includeList = new ArrayList<>();
    private List<Extend> extendList = new ArrayList<>();
    private List<Anotation> anotationList = new ArrayList<>();


    //Action modes
    public static final int CREATE_USE_CASE = 10;




    /*private UseCase useCase;
    private UseCase useCase2;
    private Association association;
    private Actor actor;
    private Actor actor2;
    private Extend extend;
    private Include include;*/

    public Sketch(Context sketchContext) {
        super();
        this.setSketchContext(sketchContext);

        /*this.useCase = new UseCase(0, 200, 200, "Realizar Login", this);
        this.useCase2 = new UseCase(0, 400, 600, "Realizar Login", this);
        this.association = new Association(0, 1, 1, this);
        this.association.setPositions(useCase.getX(), useCase.getY(), useCase2.getX(), useCase2.getY());
        this.actor = new Actor(0,300, 800, "Cliente", this);
        this.actor2 = new Actor(0, 300, 1600, "Gerente", this);
        //this.extend = new Extend(0, 1 , 1, this);
        //extend.setPositions(actor.getX(), actor.getY(), actor2.getX(), actor2.getY());
        this.include = new Include(0,0,0, this);
        this.include.setPositions(actor.getX(), actor.getY(), actor2.getX(), actor2.getY());*/

    }


    public void modifyActions(String key) {
        for (Option option : options)
            if (option.getKey() == key) {
                option.setValue(true);
            } else {
                option.setValue(false);
            }
    }


    @Override
    public void settings() {
        size(this.displayWidth, this.displayHeight);
    }

    @Override
    public void setup() {
    }

    @Override
    public void draw() {
        background(Color.parseColor("#ffffff"));
        lines();

        for (Include i : includeList) {
            int x1, y1, x2, y2;
            x1 = x2 = y1 = y2 = 0;
            for (UseCase u : useCaseList) {
                if (u.getId() == i.getFrom()) {
                    x1 = u.getCenter()[0];
                    y1 = u.getCenter()[1];
                } else if (u.getId() == i.getTo()) {
                    x2 = u.getCenter()[0];
                    y2 = u.getCenter()[1];
                }
            }
            i.setPositions(x1, y1, x2, y2);
            i.draw();
        }

        for (Extend e : extendList) {
            int x1, y1, x2, y2;
            x1 = x2 = y1 = y2 = 0;
            for (UseCase u : useCaseList) {
                if (u.getId() == e.getFrom()) {
                    x1 = u.getCenter()[0];
                    y1 = u.getCenter()[1];
                } else if (u.getId() == e.getTo()) {
                    x2 = u.getCenter()[0];
                    y2 = u.getCenter()[1];
                }
            }
            e.setPositions(x1, y1, x2, y2);
            e.draw();
        }

        for (UseCase u : useCaseList)
            u.draw(mouseX, mouseY);

        for (Actor a : actorList)
            a.draw(mouseX, mouseY);


    }

    public void touchEnded() {
        checkActions("E");

        for (UseCase useCase : useCaseList) {
            useCase.setLocked(false);
        }

        for (Actor actor : actorList) {
            actor.setLocked(false);
        }


    }

    public void touchStarted() {
        // Log.v("APP", "MouseX: " + mouseX + " MouseY: " + mouseY);
        /*if (wichIsTrue().equalsIgnoreCase("pointer")) {
            for (UseCase useCase : useCaseList) {
                useCase.setLocked(useCase.isHover() ? true : false);
                useCase.setOffset_x(mouseX - useCase.getX());
                useCase.setOffset_y(mouseY - useCase.getY());
            }
        }*/

        checkActions("S");
    }

    public void touchMoved() {
        if (wichIsTrue().equalsIgnoreCase("pointer")) {
            for (UseCase useCase : useCaseList) {
                if (useCase.isHover()) {
                    useCase.setX(mouseX - useCase.getOffset_x());
                    useCase.setY(mouseY - useCase.getOffset_y());
                    return;
                }
            }

            for (Actor actor : actorList) {
                if (actor.isHover()) {
                    actor.setX(mouseX - actor.getOffset_x());
                    actor.setY(mouseY - actor.getOffset_y());
                    return;
                }
            }
        }
    }

    private void checkActions(String action) {
        String key = "";
        for (Option option : options) {
            if (option.isValue()) key = option.getKey();
        }

        Log.i("APP", "KEY: " + key);
        if (!key.isEmpty()) {
            if (key.equals("usecase") && action.equals("E")) {
                useCaseList.add(new UseCase(getId(), mouseX, mouseY, "Teste", this));
            } else if (key.equals("actor") && action.equals("E")) {
                actorList.add(new Actor(getId(), mouseX, mouseY, "Cliente", this));
            } else if ((key.equals("include") || key.equals("extend")) && action.equals("E")) {
                for (UseCase useCase : useCaseList) {
                    if (useCase.isHover()) {
                        tempCase.add(useCase);
                        break;
                    }
                }
                if (tempCase.size() == 2) {
                    if (key.equals("include")) {
                        includeList.add(new Include(getId(), tempCase.get(0).getId(), tempCase.get(1).getId(), this));
                    } else {
                        extendList.add(new Extend(getId(), tempCase.get(0).getId(), tempCase.get(1).getId(), this));
                    }
                    clearTemp();
                }
            }
                  /*for (UseCase useCase : useCaseList) {
                        useCase.setLocked(useCase.isHover() ? true : false);
                        useCase.setOffset_x(mouseX - useCase.getX());
                        useCase.setOffset_y(mouseY - useCase.getY());
                    }
                    break;*/
        }
    }


    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }


    public Context getSketchContext() {
        return sketchContext;
    }

    public void setSketchContext(Context sketchContext) {
        this.sketchContext = sketchContext;
    }

    private ArrayList<Option> buildOptions() {
        ArrayList<Option> options = new ArrayList<>();
        options.add(new Option("pointer", true));
        options.add(new Option("usecase", false));
        options.add(new Option("actor", false));
        options.add(new Option("association", false));
        options.add(new Option("annotation", false));
        options.add(new Option("include", false));
        options.add(new Option("extend", false));

        return options;
    }

    private void lines() {
        int gapSize = 40;
        int numColumns = displayWidth / gapSize;
        int numRows = displayHeight / gapSize;
        int x = 0;
        int y = 0;

        strokeWeight(1);
        stroke(Color.parseColor("#000000"));

        for (int i = 0; i < numRows; i++) {
            y += gapSize;
            line(0, y, displayWidth, y);
        }

        for (int i = 0; i < numColumns; i++) {
            x += gapSize;
            line(x, 0, x, displayHeight);
        }
    }

    public void clearTemp() {
        tempCase.clear();
    }


    public void wichIsTrue(Context context) {
        String key = "";
        for (Option option : options)
            if (option.isValue()) key = option.getKey();

        Toast.makeText(context, "Mode: " + key, Toast.LENGTH_SHORT).show();
    }

    public String wichIsTrue() {
        String key = "";
        for (Option option : options)
            if (option.isValue()) key = option.getKey();

        return key;
    }


    private int getId() {
        return this.id++;
    }


}
