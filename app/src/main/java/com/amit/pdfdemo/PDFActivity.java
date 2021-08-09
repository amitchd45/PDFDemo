package com.amit.pdfdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class PDFActivity extends AppCompatActivity {
    private PDFView pdfView;
    private String pdfUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfactivity);
        pdfView = findViewById(R.id.pdfView);

        pdfUrl=getIntent().getStringExtra("url");


    }
}