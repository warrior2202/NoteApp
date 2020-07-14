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

import com.example.etrycatch.Adapters.SubjectListAdapter;
import com.example.etrycatch.R;
import com.example.etrycatch.Rest.ApiClient;
import com.example.etrycatch.Rest.ApiInterface;
import com.example.etrycatch.models.SubjectListPogo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Subjectist extends AppCompatActivity {
    private List<SubjectListPogo> subjectListPogos;
    private ProgressDialog pDialog;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
//    private String id;

    private List<SubjectListPogo> subjectists ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjectist);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        recyclerView = findViewById(R.id.subject_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<SubjectListPogo>> call = apiService.getSubjectlist("4");
        call.enqueue(new Callback<List<SubjectListPogo>>() {
            @Override
            public void onResponse(Call<List<SubjectListPogo>> call, Response<List<SubjectListPogo>> response) {
                subjectists = response.body();
                SubjectListAdapter subjectListAdapter = new SubjectListAdapter(Subjectist.this, subjectists);
                recyclerView.setAdapter(subjectListAdapter);
                recyclerView.addOnItemTouchListener(new com.example.etrycatch.Activity.MainActivity.RecyclerTouchListener(getApplicationContext(), recyclerView, new com.example.etrycatch.Activity.MainActivity.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {

                        Intent abc = new Intent(Subjectist.this, com.example.etrycatch.Activity.Notes.class);
                        abc.putExtra("subject_id", subjectists.get(position).getSubject_id());
                        abc.putExtra("cat_id", subjectists.get(position).getCat_id());
                        startActivity(abc);

                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));

//                Intent abc = new Intent(Subjectist.this, Notes.class);
//                abc.putExtra("subject_id", subjectists.get(1).getSubject_id());
//                abc.putExtra("cat_id", subjectists.get(1).getCat_id());

//                startActivity(abc);
//                Toast.makeText(Subjectist.this, subjectists.get(0).getSubject(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<List<SubjectListPogo>> call, Throwable t) {

            }
        });

    }
        public interface ClickListener {
            void onClick(View view, int position);
            void onLongClick(View view, int position);
        }
        public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

            private GestureDetector gestureDetector;
            private Subjectist.ClickListener clickListener;
            public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final Subjectist.ClickListener clickListener) {
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