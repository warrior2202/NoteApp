package com.example.noteappjava.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteappjava.R;
import com.example.noteappjava.models.SubjectListPogo;

import java.util.List;

public class SubjectListAdapter extends  RecyclerView.Adapter<SubjectListAdapter.Myviewholder>  {
        Context mcontext;
        List<SubjectListPogo> subjectListPogos;

    public SubjectListAdapter(Context mcontext, List<SubjectListPogo> subjectListPogos) {
        this.mcontext = mcontext;
        this.subjectListPogos = subjectListPogos;
    }

    @NonNull
        @Override
        public Myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v;
            v = LayoutInflater.from( mcontext ).inflate( R.layout.mainsinglelayout ,viewGroup,false);
            Myviewholder myviewholder =new Myviewholder( v );
            return myviewholder;
        }

        @Override
        public void onBindViewHolder(@NonNull Myviewholder myviewholder, int i) {
            myviewholder.tv_name.setText( subjectListPogos.get( i ).getSubject() );

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
