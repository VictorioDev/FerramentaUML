package com.aula.victoriozansavio.umlp5.inteface;

import com.aula.victoriozansavio.umlp5.library.Correction;
import com.aula.victoriozansavio.umlp5.library.Submission;

public interface SubmissionActionInterface {

    void OnSubmissionSaved(Submission submission);

    void OnSubmissionCorrection(Correction correction);
}
