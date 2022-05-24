package br.org.lsitec.android.quizx.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import br.org.lsitec.android.quizx.databinding.FragmentEndgameBinding;

public class EndgameFragment extends Fragment {

    private FragmentEndgameBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEndgameBinding.inflate(inflater, container, false);

        int score = 0;
        int numQuestions = 0;
        int percentage = 0;

        if (getArguments() != null) {
            score = getArguments().getInt("score");
            numQuestions = getArguments().getInt("numQuestions");
            percentage = (score * 100) / numQuestions;
        }

        binding.endgameProgress.setProgress(percentage);
        binding.endgamePercentageScore.setText(percentage + "%");
        binding.endgameScore.setText("You got " + score + " out of " + numQuestions);
        binding.endgamePlayAgain.setOnClickListener(view ->
                Navigation.findNavController(view)
                        .navigate(EndgameFragmentDirections.actionEndgameFragmentToGameFragment())
        );

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}