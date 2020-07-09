package com.example.noteappjava.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteappjava.R;
import com.example.noteappjava.models.NoteListPgo;
import com.example.noteappjava.models.SubjectListPogo;

import java.util.List;
public class NotesAdapter extends  RecyclerView.Adapter<NotesAdapter.Myviewholder>  {
        Context mcontext;
        List<NoteListPgo> subjectListPogos;

    public NotesAdapter(Context mcontext, List<NoteListPgo> subjectListPogos) {
        this.mcontext = mcontext;
        this.subjectListPogos = subjectListPogos;
    }

    @NonNull
        @Override
        public com.example.noteappjava.Adapters.NotesAdapter.Myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v;
            v = LayoutInflater.from( mcontext ).inflate( R.layout.mainsinglelayout ,viewGroup,false);
            com.example.noteappjava.Adapters.NotesAdapter.Myviewholder myviewholder =new com.example.noteappjava.Adapters.NotesAdapter.Myviewholder( v );
            return myviewholder;
        }

        @Override
        public void onBindViewHolder(@NonNull com.example.noteappjava.Adapters.NotesAdapter.Myviewholder myviewholder, int i) {
            myviewholder.tv_name.setText( subjectListPogos.get( i ).getNotes_title() );

        }

        @Override
        public int getItemCount() {
            return subjectListPogos.size();
        }

        public static  class Myviewholder extends RecyclerView.ViewHolder{
            private TextView tv_name;

            public Myviewholder(@NonNull View itemView) {
                super( itemView );
                tv_name = (TextView) itemView.findViewById( R.id.txt_subject_title );
            }
        }


    }
