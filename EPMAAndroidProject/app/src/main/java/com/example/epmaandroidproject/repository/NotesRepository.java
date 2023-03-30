package com.example.epmaandroidproject.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.epmaandroidproject.data.NoteDao;
import com.example.epmaandroidproject.data.NotesDatabase;
import com.example.epmaandroidproject.model.Note;

import java.util.List;

public class NotesRepository {
    private NoteDao noteDao;

    private LiveData<List<Note>> readAllData;

    public NotesRepository(Application application){
        NotesDatabase notesDatabase = NotesDatabase.getDatabase(application);
        noteDao = notesDatabase.noteDao();
        readAllData = noteDao.readAllData();
    }

    public void addNote(Note note){
        new AddNoteAsync(noteDao).execute(note);

    }

    public LiveData<List<Note>> getAllNotes(){
        return readAllData;
    }

    private static class AddNoteAsync extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        private AddNoteAsync(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... note) {
            noteDao.addNote(note[0]);
            return null;
        }
    }
}
