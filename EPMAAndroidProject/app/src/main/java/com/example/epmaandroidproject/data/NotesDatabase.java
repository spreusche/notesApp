package com.example.epmaandroidproject.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.epmaandroidproject.model.Note;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class NotesDatabase extends RoomDatabase {

    public abstract NoteDao noteDao();

    static volatile private NotesDatabase INSTANCE = null;

    public synchronized static NotesDatabase getDatabase(Context context){
        NotesDatabase tempInstance = INSTANCE;
        if(tempInstance != null)
            return tempInstance;

            NotesDatabase instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    NotesDatabase.class,
                    "notes"
            )
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
            INSTANCE = instance;
            return instance;

    }

    // below line is to create a callback for our room database.
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            // this method is called when database is created
            // and below line is to populate our data.
            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    // we are creating an async task class to perform task in background.
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        PopulateDbAsyncTask(NotesDatabase instance) {
            NoteDao dao = INSTANCE.noteDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }



}
