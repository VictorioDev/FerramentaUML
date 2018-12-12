package com.aula.victoriozansavio.umlp5;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aula.victoriozansavio.umlp5.component.Actor;
import com.aula.victoriozansavio.umlp5.component.Annotation;
import com.aula.victoriozansavio.umlp5.component.Association;
import com.aula.victoriozansavio.umlp5.component.Extend;
import com.aula.victoriozansavio.umlp5.component.Generalization;
import com.aula.victoriozansavio.umlp5.component.Include;
import com.aula.victoriozansavio.umlp5.component.UseCase;
import com.aula.victoriozansavio.umlp5.inteface.DialogActionsInterface;
import com.aula.victoriozansavio.umlp5.library.JsonStructure;
import com.aula.victoriozansavio.umlp5.library.Option;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import processing.core.PApplet;

/**
 * Created by Victorio Zansavio on 05/10/2018.
 */

public class Sketch extends PApplet implements DialogActionsInterface {

    // Main Variables
    private ArrayList<UseCase> tempCase = new ArrayList<>();
    private ArrayList<Actor> tempActor = new ArrayList<>();
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
    private List<Annotation> annotationList = new ArrayList<>();
    private DialogActionsInterface dialogActionsInterface = this;

    private String json = "";



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
        //this.useCaseList = mockData();

    }

    public Sketch(Context sketchContext, String json) {
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
        //this.useCaseList = mockData();
        this.json = json;
        Log.i("App", "JsonBeforeConvert: " + json);
        convertToList();
    }

    private void convertToList() {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        JsonStructure jsonStructure = gson.fromJson(json, JsonStructure.class);
        if(jsonStructure != null){
            Log.i("App", "JsonSize:" + jsonStructure.getUseCaseList().size());
            for (UseCase useCase : jsonStructure.getUseCaseList()){
                UseCase u = new UseCase();
                u.setX(useCase.getX());
                u.setY(useCase.getY());
                u.setId(useCase.getId());
                u.setName(useCase.getName());
                u.setDrawContainer(this);
                useCaseList.add(u);
            }

            for (Actor actor: jsonStructure.getActorList()){
                Actor a = new Actor();
                a.setX(actor.getX());
                a.setY(actor.getY());
                a.setId(actor.getId());
                a.setName(actor.getName());
                a.setDrawContainer(this);
                actorList.add(a);
            }

            for(Extend extend: jsonStructure.getExtendList()){
                Extend e = new Extend();
                e.setId(extend.getId());
                e.setFrom(extend.getFrom());
                e.setTo(extend.getTo());
                e.setDrawContainer(this);
                extendList.add(e);
            }

            for(Include include: jsonStructure.getIncludeList()){
                Include i = new Include();
                i.setId(include.getId());
                i.setFrom(include.getFrom());
                i.setTo(include.getTo());
                i.setDrawContainer(this);
                includeList.add(i);
            }

            for(Association association: jsonStructure.getAssociationList()){
                Association ass = new Association();
                ass.setId(association.getId());
                ass.setFrom(association.getFrom());
                ass.setTo(association.getTo());
                ass.setDrawContainer(this);
                associationList.add(ass);
            }

            for(Annotation annotation : jsonStructure.getAnnotationList()){
                Annotation ass =  new Annotation();
                ass.setId(annotation.getId());
                ass.setX(annotation.getX());
                ass.setY(annotation.getY());
                ass.setText(annotation.getText());
                ass.setDrawContainer(this);
                annotationList.add(ass);
            }

        }


    }


    public String saveToJson(){
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        JsonStructure jsonStructure = new JsonStructure();

        jsonStructure.setUseCaseList(useCaseList);
        jsonStructure.setActorList(actorList);
        jsonStructure.setExtendList(extendList);
        jsonStructure.setIncludeList(includeList);
        jsonStructure.setAssociationList(associationList);
        jsonStructure.setAnnotationList(annotationList);
        return gson.toJson(jsonStructure);
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

        for (Association association : associationList) {
            int x1, y1, x2, y2;
            x1 = x2 = y1 = y2 = 0;
            for (UseCase u : useCaseList) {
               if (u.getId() == association.getTo()) {
                    x2 = u.getCenter()[0];
                    y2 = u.getCenter()[1];
                }
            }

            for (Actor actor : actorList) {
               if (actor.getId() == association.getFrom()) {
                    x1 = actor.getCenter()[0];
                    y1 = actor.getCenter()[1];
                }
            }
            association.setPositions(x1, y1, x2, y2);
            association.draw();
        }



        for (UseCase u : useCaseList)
            u.draw(mouseX, mouseY);

        for (Actor a : actorList)
            a.draw(mouseX, mouseY);

        for(Annotation annotation: annotationList){
            annotation.draw();
            //Log.i("App", "Anotation: " + annotation.getText());
        }

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
                showDInputDialog("Caso de Uso", dialogActionsInterface);
                //useCaseList.add(new UseCase(getId(), mouseX, mouseY, "Teste", this));
            } else if (key.equals("actor") && action.equals("E")) {
                showDInputDialog("Ator", dialogActionsInterface);
                //actorList.add(new Actor(getId(), mouseX, mouseY, "Cliente", this));
            } else if ((key.equals("include") || key.equals("extend") || key.equals("association")) && action.equals("E")) {
                for (UseCase useCase : useCaseList) {
                    if (useCase.isHover()) {
                        tempCase.add(useCase);
                        Log.i("App", "Temp Added: " + useCase.getId());
                    }
                }

                for (Actor actor: actorList) {
                    if (actor.isHover()) {
                        tempActor.add(actor);
                        Log.i("App", "Temp Added: " + actor.getId());
                    }
                }

                if (tempCase.size() == 2 && (tempCase.get(0).getId() != tempCase.get(1).getId())) {
                    if (key.equals("include")) {
                        includeList.add(new Include(getId(), tempCase.get(0).getId(), tempCase.get(1).getId(), this));
                    } else {
                        extendList.add(new Extend(getId(), tempCase.get(0).getId(), tempCase.get(1).getId(), this));
                    }
                    clearTemp();
                }else if(tempCase.size() == 1 && tempActor.size() == 1){
                    Log.i("App", "Association Added");
                    associationList.add(new Association(getId(), tempActor.get(0).getId(), tempCase.get(0).getId(), this));
                    clearTemp();
                }
            }else if(key.equals("annotation") && action.equals("E")){
                showDInputDialog("Anotação", dialogActionsInterface);
                //annotationList.add(new Annotation(getId(), mouseX, mouseY, "Anotacao", this));
            }
                  /*for (UseCase useCase : useCaseList) {
                        useCase.setLocked(useCase.isHover() ? true : false);
                        useCase.setOffset_x(mouseX - useCase.getX());
                        useCase.setOffset_y(mouseY - useCase.getY());
                    }
                    break;*/
        }
    }

    private void showDInputDialog(final String action, final DialogActionsInterface dialogActionsInterface){
        ((AppCompatActivity) sketchContext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Dialog dialog = new Dialog(sketchContext);
                dialog.setCanceledOnTouchOutside(false);
                dialog.setContentView(R.layout.dialog_input);
                Button btnIncluir = dialog.findViewById(R.id.dialog_novo_btnIncluir);
                Button btnCancelar = dialog.findViewById(R.id.dialog_novo_btnCancelar);
                TextView tvTiop = dialog.findViewById(R.id.dialog_novo_tvTipo);
                tvTiop.setText(action);

                btnCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                final EditText edtTexto = dialog.findViewById(R.id.dialog_novo_edtTexto);
                btnIncluir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogActionsInterface.onTextIncluded(edtTexto.getText().toString(), action);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

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
        tempActor.clear();
    }


    public void wichIsTrue(Context context) {
        String key = "";
        for (Option option : options)
            if (option.isValue()) key = option.getKey();

        //Toast.makeText(context, "Mode: " + key, Toast.LENGTH_SHORT).show();
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


    @Override
    public void onTextIncluded(String text, String action) {
        if(action.equals("Caso de Uso")){
            useCaseList.add(new UseCase(getId(), mouseX, mouseY, text, this));
        }else if(action.equals("Ator")){
            actorList.add(new Actor(getId(), mouseX, mouseY, text, this));
        }else if(action.equals("Anotação")){
            annotationList.add(new Annotation(getId(), mouseX, mouseY, text, this));
            Log.i("App", "AnnotationList: " + annotationList.size());
        }
    }
}
