package com.example.mobile06454;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText edkode, ednama, edsatuan, edharga;
    DatabaseReference dbr;
    Button tombolsave;
    ModelBarang modelBarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edkode=findViewById(R.id.kodebrg);
        ednama=findViewById(R.id.namabrg);
        edsatuan=findViewById(R.id.satuanbrg);
        edharga=findViewById(R.id.hargabrg);
        modelBarang=new ModelBarang();
        dbr= FirebaseDatabase.getInstance().getReference().child("StokBarang");

        tombolsave=findViewById(R.id.tombolsimpan);
        tombolsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelBarang.setKode(edkode.getText().toString());
                modelBarang.setNama_brg(ednama.getText().toString());
                modelBarang.setSatuan(edsatuan.getText().toString());
                modelBarang.setHarga(Integer.parseInt(edharga.getText().toString()));

                dbr.push().setValue(modelBarang);
            }
        });
    }
}