package br.org.lsitec.android.quizx.ui.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import java.util.Collections;
import java.util.List;

import br.org.lsitec.android.quizx.R;
import br.org.lsitec.android.quizx.databinding.FragmentGameBinding;
import br.org.lsitec.android.quizx.model.Question;
import br.org.lsitec.android.quizx.repository.QuizRepository;

public class GameFragment extends Fragment {

    private static final String TAG = "GameFragment";
    private FragmentGameBinding binding;
    private List<Question> questions;
    private Question currentQuestion;
    private List<String> answers;
    private int correctAnswerIndex;
    private int numQuestions;
    private int score;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGameBinding.inflate(inflater, container, false);

        QuizRepository repository = new QuizRepository();
        repository.getQuestions(result -> {
            questions = result;
            numQuestions = questions.size();
            setQuiz();
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setQuiz() {
        initializeQuestion();
        binding.gameQuestion.setText(currentQuestion.getQuestion());
        binding.gameFirstAnswer.setText(answers.get(0));
        binding.gameSecondAnswer.setText(answers.get(1));
        binding.gameThirdAnswer.setText(answers.get(2));
        binding.gameFourthAnswer.setText(answers.get(3));
        configureSubmitButton();
    }

    private void initializeQuestion() {
        currentQuestion = questions.remove(0);
        answers = currentQuestion.getIncorrectAnswer();
        answers.add(currentQuestion.getCorrectAnswer());
        Collections.shuffle(answers);
    }

    private void configureSubmitButton() {
        binding.gameSubmitButton.setOnClickListener(view -> {
            int checkedAnswer = binding.gameAnswerGroup.getCheckedRadioButtonId();
            if (checkedAnswer >= 0) {
                int userAnswerIndex = getAnswerIndex(checkedAnswer);

                correctAnswerIndex = answers.indexOf(currentQuestion.getCorrectAnswer());

                checkUserAnswer(userAnswerIndex);

                if (questions.isEmpty()) {
                    Navigation.findNavController(view)
                            .navigate(GameFragmentDirections.actionGameFragmentToEndgameFragment(score, numQuestions));
                } else {
                    reloadQuiz();
                }
            }
        });
    }

    private void reloadQuiz() {
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            setQuiz();
            binding.gameAnswerGroup.getChildAt(correctAnswerIndex)
                    .setBackgroundColor(Color.TRANSPARENT);
        }, 500);
    }

    private void checkUserAnswer(int userAnswerIndex) {
        if (userAnswerIndex == correctAnswerIndex) {
            score++;
        }
        int green = ContextCompat.getColor(requireContext(), R.color.colorPrimaryVariant);
        binding.gameAnswerGroup.getChildAt(correctAnswerIndex)
                .setBackgroundColor(green);
    }

    private int getAnswerIndex(int checkedAnswer) {
        if (checkedAnswer == R.id.game_second_answer) return 1;
        if (checkedAnswer == R.id.game_third_answer) return 2;
        if (checkedAnswer == R.id.game_fourth_answer) return 3;
        return 0;
    }
}
