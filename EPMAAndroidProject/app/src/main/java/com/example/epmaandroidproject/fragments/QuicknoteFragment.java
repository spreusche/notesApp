package com.example.epmaandroidproject.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.epmaandroidproject.R;
import com.example.epmaandroidproject.model.Note;
import com.example.epmaandroidproject.viewmodel.NoteViewModel;

public class QuicknoteFragment extends Fragment {

    private NoteViewModel noteViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getActivity().setTitle("Quicknote");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        View view = inflater.inflate(R.layout.fragment_quicknote, container, false);
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        noteViewModel = viewModelProvider.get(NoteViewModel.class);
        Button saveButton = view.findViewById(R.id.saveNote);
        saveButton.setOnClickListener(view1 -> {
            String quickNote = ((EditText) view.findViewById(R.id.newNoteET)).getText().toString();
            String title = ((EditText) view.findViewById(R.id.noteTitle)).getText().toString();

            if(quickNote.equals(""))
                Toast.makeText(requireContext(), "Please add text to your note", Toast.LENGTH_LONG).show();
            else {
                Note note = new Note(quickNote, title);
                noteViewModel.addNote(note);
                Toast.makeText(requireContext(), "Note created", Toast.LENGTH_LONG).show();
                Navigation.findNavController(view).navigate(R.id.noteBoard_fragment);
            }
        });
        Button myNotesBtn = view.findViewById(R.id.myNotesBtn);
        myNotesBtn.setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.noteBoard_fragment);

        });
        return view;
    }


}
