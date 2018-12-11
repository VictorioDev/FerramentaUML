package com.aula.victoriozansavio.umlp5.inteface;

import com.aula.victoriozansavio.umlp5.library.Exercise;

import java.util.List;

public interface ExerciseActionInterface {

    void onExercisesRetrieved(List<Exercise> exerciseList);

    void onExerciseDeleted(int position);

    void onExerciseSaved();

    void onExerciseEdited();
}
