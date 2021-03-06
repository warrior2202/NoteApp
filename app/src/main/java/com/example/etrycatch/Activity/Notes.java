package com.example.etrycatch.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.example.etrycatch.Adapters.NotesAdapter;
import com.example.etrycatch.R;
import com.example.etrycatch.Rest.ApiClient;
import com.example.etrycatch.Rest.ApiInterface;
import com.example.etrycatch.models.NoteListPgo;
import com.example.etrycatch.models.SubjectListPogo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Notes extends AppCompatActivity {
    String cat_id;
    String subject_id;
    private List<SubjectListPogo> subjectListPogos;
    private ProgressDialog pDialog;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private List<NoteListPgo> noteListPgos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_notes);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        Intent intent = getIntent();
        cat_id = intent.getStringExtra("cat_id");
        subject_id = intent.getStringExtra("subject_id");
        recyclerView = findViewById(R.id.notesrecycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        ApiInterface apiService = ApiClient.getClient().create( ApiInterface.class );
        Call<List<NoteListPgo>> call = apiService.getnotelist(cat_id,subject_id);
        call.enqueue(new Callback<List<NoteListPgo>>() {
            @Override
            public void onResponse(Call<List<NoteListPgo>> call, Response<List<NoteListPgo>> response) {
                noteListPgos = response.body();
                NotesAdapter notesAdapter = new NotesAdapter(Notes.this,noteListPgos);
                recyclerView.setAdapter(notesAdapter);
                recyclerView.addOnItemTouchListener(new MainActivity.RecyclerTouchListener(Notes.this, recyclerView, new MainActivity.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Intent abc = new Intent(Notes.this,pdfView.class);
                        abc.putExtra("filename",noteListPgos.get(position).getNotes_pdf());
                        abc.putExtra("name",noteListPgos.get(position).getNotes_title());
                        startActivity(abc);

                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));
                Intent abc = new Intent(Notes.this,pdfView.class);
                abc.putExtra("filename",noteListPgos.get(0).getNotes_pdf());
//                startActivity(abc);

            }

            @Override
            public void onFailure(Call<List<NoteListPgo>> call, Throwable t) {

            }
        });





    }

    public interface ClickListener {
        void onClick(View view, int position);
        void onLongClick(View view, int position);
    }
    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private Notes.ClickListener clickListener;
        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final Notes.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildAdapterPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }
    }

}
