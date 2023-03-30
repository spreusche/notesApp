package com.example.epmaandroidproject.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epmaandroidproject.R;
import com.example.epmaandroidproject.model.Note;

import java.util.ArrayList;
import java.util.List;

public class ListNotesAdapter extends ListAdapter<Note, ListNotesAdapter.ViewHolder> {

    //private List<Note> notes = new ArrayList<>();

    ListNotesAdapter() {
        super(DIFF_CALLBACK);
    }


    // creating a call back for item of recycler view.
    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(Note oldItem, Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(Note oldItem, Note newItem) {
            // below line is to check the course name, description and course duration.
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getText().equals(newItem.getText());
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.note_card, parent, false));
    }

    //private int getNotesCount(){
    //    return notes.size();
    //}

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note currentNote = getItem(position);
        holder.titleTV.setText(currentNote.getTitle());
        holder.textTV.setText(currentNote.getText());
//        ((TextView) holder.itemView.findViewById(R.id.title_txt)).setText(currentNote.getTitle());
//        ((TextView) holder.itemView.findViewById(R.id.text_txt)).setText(currentNote.getText());

        holder.itemView.findViewById(R.id.rowLayout).setOnClickListener(view1 -> {
                ListNotesFragmentDirections.ActionNoteBoardFragmentToNoteInfoFragment ac =  ListNotesFragmentDirections.actionNoteBoardFragmentToNoteInfoFragment(holder.textTV.getText().toString(), holder.titleTV.getText().toString()); //QuicknoteFragmentDirections.ac(quickNote.getText().toString());
                Navigation.findNavController(holder.itemView).navigate(ac);
        });


    }

//    public void setData(List<Note> notes){
//        this.notes = notes;
//        notifyDataSetChanged();
//    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView titleTV, textTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.title_txt);
            textTV = itemView.findViewById(R.id.text_txt);
        }
    }
}
