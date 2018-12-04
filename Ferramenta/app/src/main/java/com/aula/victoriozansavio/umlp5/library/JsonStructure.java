package com.aula.victoriozansavio.umlp5.library;

import com.aula.victoriozansavio.umlp5.component.UseCase;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victorio Zansavio on 03/12/2018.
 */

public class JsonStructure {

    @SerializedName("use_case_list")
    List<UseCase> useCaseList = new ArrayList<>();

    public List<UseCase> getUseCaseList() {
        return useCaseList;
    }

    public void setUseCaseList(List<UseCase> useCaseList) {
        this.useCaseList = useCaseList;
    }

}
