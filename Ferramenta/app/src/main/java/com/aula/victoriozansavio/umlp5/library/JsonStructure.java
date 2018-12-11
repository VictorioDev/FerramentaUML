package com.aula.victoriozansavio.umlp5.library;

import com.aula.victoriozansavio.umlp5.component.Actor;
import com.aula.victoriozansavio.umlp5.component.Annotation;
import com.aula.victoriozansavio.umlp5.component.Association;
import com.aula.victoriozansavio.umlp5.component.Extend;
import com.aula.victoriozansavio.umlp5.component.Include;
import com.aula.victoriozansavio.umlp5.component.UseCase;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victorio Zansavio on 03/12/2018.
 */

public class JsonStructure {

    @Expose
    @SerializedName("altura")
    private int altura;

    @Expose
    @SerializedName("largura")
    private int largura;

    @Expose
    @SerializedName("cases")
    private List<UseCase> useCaseList = new ArrayList<>();


    @Expose
    @SerializedName("atores")
    private List<Actor> actorList = new ArrayList<>();

    @Expose
    @SerializedName("includes")
    private List<Include> includeList = new ArrayList<>();

    @Expose
    @SerializedName("extends")
    private List<Extend> extendList = new ArrayList<>();

    @Expose
    @SerializedName("associacoes")
    private List<Association> associationList = new ArrayList<>();

    @Expose
    @SerializedName("observacoes")
    private List<Association> observationList = new ArrayList<>();

    @Expose
    @SerializedName("anotacoes")
    private List<Annotation> annotationList = new ArrayList<>();

    public List<Annotation> getAnnotationList() {
        return annotationList;
    }

    public void setAnnotationList(List<Annotation> annotationList) {
        this.annotationList = annotationList;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public List<UseCase> getUseCaseList() {
        return useCaseList;
    }

    public void setUseCaseList(List<UseCase> useCaseList) {
        this.useCaseList = useCaseList;
    }

    public List<Actor> getActorList() {
        return actorList;
    }

    public void setActorList(List<Actor> actorList) {
        this.actorList = actorList;
    }

    public List<Include> getIncludeList() {
        return includeList;
    }

    public void setIncludeList(List<Include> includeList) {
        this.includeList = includeList;
    }

    public List<Extend> getExtendList() {
        return extendList;
    }

    public void setExtendList(List<Extend> extendList) {
        this.extendList = extendList;
    }

    public List<Association> getAssociationList() {
        return associationList;
    }

    public void setAssociationList(List<Association> associationList) {
        this.associationList = associationList;
    }

    public List<Association> getObservationList() {
        return observationList;
    }

    public void setObservationList(List<Association> observationList) {
        this.observationList = observationList;
    }
}
