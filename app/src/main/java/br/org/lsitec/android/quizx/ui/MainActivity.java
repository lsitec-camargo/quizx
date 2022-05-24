package br.org.lsitec.android.quizx.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import br.org.lsitec.android.quizx.R;
import br.org.lsitec.android.quizx.model.Question;
import br.org.lsitec.android.quizx.webclient.RetrofitInstance;
import br.org.lsitec.android.quizx.webclient.service.QuizService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}