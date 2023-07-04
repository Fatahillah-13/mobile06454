package com.example.mobile06454;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainFirebaseBarang extends AppCompatActivity {

    RecyclerView recyclerViewbarang;
    DatabaseReference dbr;
    ArrayList<ModelBarang>modelBarangArrayList=new ArrayList<>();
    ModelBarang modelBarang;
    FloatingActionButton tombolinput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_firebase_barang);

        recyclerViewbarang=findViewById(R.id.rv_barang);
        tombolinput=findViewById(R.id.inputtombol);

        dbr= FirebaseDatabase.getInstance().getReference();
        tampil_barang();
        recyclerViewbarang.setLayoutManager(new LinearLayoutManager(this));

        tombolinput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainFirebaseBarang.this, MainActivity.class));
            }
        });
    }

    private void tampil_barang() {
        dbr.child("StokBarang").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ModelBarang modelBarang = dataSnapshot.getValue(ModelBarang.class);
                    modelBarang.setKey(dataSnapshot.getKey());
                    modelBarangArrayList.add(modelBarang);
                }
                AdapterBarang adapterBarang=new AdapterBarang(MainFirebaseBarang.this,modelBarangArrayList);
                recyclerViewbarang.setAdapter(adapterBarang);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}