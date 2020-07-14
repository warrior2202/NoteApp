package com.example.etrycatch.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.etrycatch.PermissionUtils;
import com.example.etrycatch.R;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class pdfView extends AppCompatActivity {
    private static int splash_timeot = 5000;
    private Button download;
    private static final int STORAGE_PERMISSION_REQUEST_CODE = 1;

    String filefinal,name;
//    private static int splash_timeot = 25000;
    private ProgressDialog pDialog;
    PermissionUtils permissionUtils;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        permissionUtils = new PermissionUtils();

        filefinal = getIntent().getStringExtra( "filename" );
        name = getIntent().getStringExtra( "name" );
        setContentView( R.layout.activity_pdf_view );
        showProgDig();
        download = findViewById(R.id.download);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (permissionUtils.checkPermission(pdfView.this, STORAGE_PERMISSION_REQUEST_CODE, v)) {

                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(filefinal)));
                        } catch (Exception e) {
                            e.getStackTrace();
                        }


                }





            }
        });
        PDFView pdfView = findViewById( R.id.PdfView );

        new RetriveStream().execute( filefinal );

        Handler abc = new Handler(  );
        abc.postDelayed( new Runnable() {
            @Override
            public void run() {
                pDialog.dismiss();
            }
        },splash_timeot );
    }
    class RetriveStream extends AsyncTask<String,Void, InputStream> {
        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            try {
                URL url =new URL( strings[0] );
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                if(urlConnection.getResponseCode()==200){
                    inputStream = new BufferedInputStream( urlConnection.getInputStream() );
                }
            }
            catch (IOException e){
                return null;
            }

            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            PDFView pdfView = findViewById( R.id.PdfView );
            try {
                pdfView.fromStream( inputStream ).load();

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(pdfView.this,e.getMessage().toString(),Toast.LENGTH_LONG).show();
            }


        }




    }





    private void showProgDig() {
        pDialog = new ProgressDialog( pdfView.this );
        pDialog.setMessage( "Please wait..." );
        pDialog.setCancelable( true );
        pDialog.show();

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case STORAGE_PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Snackbar.make(urlTextInputLayout, "Permission Granted, Now you can write storage.", Snackbar.LENGTH_LONG).show();
                } else {
                    //Snackbar.make(urlTextInputLayout, "Permission Denied, You cannot access storage.", Snackbar.LENGTH_LONG).show();
                }
                break;
        }
    }
}

