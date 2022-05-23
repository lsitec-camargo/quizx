package br.org.lsitec.android.quizx.webclient.service;

import java.util.List;

import br.org.lsitec.android.quizx.model.Question;
import retrofit2.Call;
import retrofit2.http.GET;

public interface QuizService {

    @GET("questions")
    Call<List<Question>> getRandomQuestions();

}
