package com.amit.pdfdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewUploadsActivity extends AppCompatActivity {
    private RecyclerView rv_fileList;
    private DatabaseReference mDatabaseReference;
    private List<Upload> uploadList=new ArrayList<>();
    private AdapterUploadList adapterUploadList;
    private ProgressBar progress_circular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_uploads);

        initIds();
        mDatabaseReference = FirebaseDatabase.getInstance("https://pdfdemo-568b9-default-rtdb.firebaseio.com/").getReference(Constants.DATABASE_PATH_UPLOADS);
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                uploadList.clear();
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Upload upload=snapshot.getValue(Upload.class);
                    uploadList.add(upload);

                }
                adapterUploadList=new AdapterUploadList(ViewUploadsActivity.this, uploadList, new AdapterUploadList.Select() {
                    @Override
                    public void onClick(String title,String url) {

                        startActivity(new Intent(ViewUploadsActivity.this, PDFActivity.class).putExtra("url",url));


                        Log.i("filename", "title: ==="+title);
                        Log.i("filename", "onClick: url==="+url);
                    }
                });
                rv_fileList.setAdapter(adapterUploadList);
                progress_circular.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progress_circular.setVisibility(View.INVISIBLE);
                Toast.makeText(ViewUploadsActivity.this, ""+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initIds() {
        rv_fileList=findViewById(R.id.rv_fileList);
        progress_circular=findViewById(R.id.progress_circular);
    }

}