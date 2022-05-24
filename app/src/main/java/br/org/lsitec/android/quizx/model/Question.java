package br.org.lsitec.android.quizx.model;

import androidx.annotation.NonNull;

import java.util.List;

public class Question {

    private String question;
    private List<String> incorrectAnswers;
    private String correctAnswer;

    public Question(String question, List<String> incorrectAnswer, String correctAnswer) {
        this.question = question;
        this.incorrectAnswers = incorrectAnswer;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getIncorrectAnswer() {
        return incorrectAnswers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    @NonNull
    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", incorrect='" + incorrectAnswers + '\'' +
                '}';
    }
}
