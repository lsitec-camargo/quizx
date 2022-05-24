package br.org.lsitec.android.quizx.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;

import br.org.lsitec.android.quizx.model.Question;
import br.org.lsitec.android.quizx.webclient.RetrofitInstance;
import br.org.lsitec.android.quizx.webclient.service.QuizService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class QuizRepository {

    private static final String TAG = "QuizRepository";
    private final QuizService quizService;

    public QuizRepository() {
        quizService = new RetrofitInstance().getQuizService();
    }

    public void getQuestions(ResponseCallback<List<Question>> callback) {
        Call<List<Question>> call = quizService.getRandomQuestions();
        call.enqueue(new Callback<List<Question>>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                }
            }
            @Override
            @EverythingIsNonNull
            public void onFailure(Call<List<Question>> call, Throwable t) {
                Log.e(TAG, "error: " + t.getMessage());
            }
        });
    }

    public interface ResponseCallback<T> {
        void onSuccess(T result);
    }

}
