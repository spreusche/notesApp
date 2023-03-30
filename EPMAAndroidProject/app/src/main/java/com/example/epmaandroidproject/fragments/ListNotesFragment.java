package com.example.epmaandroidproject.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.epmaandroidproject.R;

import com.example.epmaandroidproject.model.Note;
import com.example.epmaandroidproject.viewmodel.NoteViewModel;

import java.util.List;

public class ListNotesFragment extends Fragment {

    private NoteViewModel noteViewModel;
    private RecyclerView rv;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_noteboard, container, false);
        rv = view.findViewById(R.id.recyclerview);
        getActivity().setTitle("My notes");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListNotesAdapter adapter = new ListNotesAdapter();

        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(requireContext()));

        Observer<List<Note>> o = adapter::submitList;


        noteViewModel = (new ViewModelProvider(this)).get(NoteViewModel.class);
        noteViewModel.readAllData.observe(getViewLifecycleOwner(), o);

    }
}
