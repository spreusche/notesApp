package com.example.epmaandroidproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.epmaandroidproject.model.Note;
import com.example.epmaandroidproject.repository.NotesRepository;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {


    private NotesRepository notesRepository;
    public LiveData<List<Note>> readAllData;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        this.notesRepository = new NotesRepository(application);
        this.readAllData = notesRepository.getAllNotes();
    }

    public void addNote(Note note){
        notesRepository.addNote(note);
    }

    public LiveData<List<Note>> getAllNotes(){
        return readAllData;
    }

}
