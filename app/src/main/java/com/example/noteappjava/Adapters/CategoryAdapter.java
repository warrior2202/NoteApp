package com.example.noteappjava.Adapters;




import android.content.Context;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteappjava.R;
import com.example.noteappjava.models.MainPgo;

import java.util.List;

public class CategoryAdapter extends  RecyclerView.Adapter<CategoryAdapter.Myviewholder>  {
        Context mcontext;
        List<MainPgo> MainPogo;

    public CategoryAdapter(Context mcontext, List<MainPgo> mainPogo) {
        this.mcontext = mcontext;
        MainPogo = mainPogo;
    }

    @NonNull
        @Override
        public Myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v;
            v = LayoutInflater.from( mcontext ).inflate( R.layout.categorysingle ,viewGroup,false);
            Myviewholder myviewholder =new Myviewholder( v );
            return myviewholder;
        }

        @Override
        public void onBindViewHolder(@NonNull Myviewholder myviewholder, int i) {
            myviewholder.tv_name.setText( MainPogo.get( i ).getCat_name() );

        }

        @Override
        public int getItemCount() {
            return MainPogo.size();
        }

        public static  class Myviewholder extends RecyclerView.ViewHolder{
            private TextView tv_name;

            public Myviewholder(@NonNull View itemView) {
                super( itemView );
                tv_name = (TextView) itemView.findViewById( R.id.category_txt_id );
            }
        }


    }

