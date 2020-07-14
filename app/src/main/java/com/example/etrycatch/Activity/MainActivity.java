package com.example.etrycatch.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.etrycatch.Adapters.CategoryAdapter;
import com.example.etrycatch.R;
import com.example.etrycatch.Rest.ApiClient;
import com.example.etrycatch.Rest.ApiInterface;
import com.example.etrycatch.models.MainPgo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private List<MainPgo> mainPgos;
    private String id;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        recyclerView = findViewById(R.id.RecyclerViewCategoryList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<MainPgo>> call = apiService.getcategory();
        call.enqueue(new Callback<List<MainPgo>>() {
            @Override
            public void onResponse(Call<List<MainPgo>> call, Response<List<MainPgo>> response) {
                mainPgos = response.body();
                CategoryAdapter categoryAdapter = new CategoryAdapter(MainActivity.this, SelectionActivity.mainPgoList,"1");
                recyclerView.setAdapter(categoryAdapter);
                recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Intent abc = new Intent(MainActivity.this, Subjectist.class);

                        abc.putExtra("id", mainPgos.get(position).getId());
                        abc.putExtra("stream", mainPgos.get(position).getStream());
                        startActivity(abc);

                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));

//                Toast.makeText(MainActivity.this, mainPgos.get(0).getCat_name(), Toast.LENGTH_LONG).show();
//                Intent abc = new Intent(MainActivity.this, Subjectist.class);
//
//                abc.putExtra("id", mainPgos.get(3).getId());
//                abc.putExtra("id", mainPgos.get(3).getId());
//                startActivity(abc);
            }

            @Override
            public void onFailure(Call<List<MainPgo>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();


            }
        });

    }


        public interface ClickListener {
            void onClick(View view, int position);
            void onLongClick(View view, int position);
        }
        public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

            private GestureDetector gestureDetector;
            private MainActivity.ClickListener clickListener;
            public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final MainActivity.ClickListener clickListener) {
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