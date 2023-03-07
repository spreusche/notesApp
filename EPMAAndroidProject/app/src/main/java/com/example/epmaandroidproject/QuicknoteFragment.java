package com.example.epmaandroidproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.epmaandroidproject.QuicknoteFragmentDirections.ActionQuicknoteFragmentToNoteBoardFragment;

public class QuicknoteFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quicknote, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button saveButton = view.findViewById(R.id.saveNote);
        EditText quickNote = view.findViewById(R.id.editTextTextMultiLine2);

        saveButton.setOnClickListener(view1 -> {
            ActionQuicknoteFragmentToNoteBoardFragment action = QuicknoteFragmentDirections.actionQuicknoteFragmentToNoteBoardFragment(quickNote.getText().toString());
            Navigation.findNavController(view).navigate(action);
        });
    }
}
