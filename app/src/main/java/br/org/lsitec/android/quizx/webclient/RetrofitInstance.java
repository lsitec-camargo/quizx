package br.org.lsitec.android.quizx.webclient;

import br.org.lsitec.android.quizx.webclient.service.QuizService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private final QuizService quizService;

    public RetrofitInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://the-trivia-api.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.quizService = retrofit.create(QuizService.class);
    }

    public QuizService getQuizService() {
        return quizService;
    }

}
