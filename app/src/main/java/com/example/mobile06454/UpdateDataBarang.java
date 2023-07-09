package com.example.mobile06454;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateDataBarang extends AppCompatActivity {

    EditText ekode,enama,esatuan,eharga;
    Button updateButton;

    DatabaseReference dbr;
    ModelBarang modelBarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data_barang);

        ekode=findViewById(R.id.kodebrg);
        enama=findViewById(R.id.namabrg);
        esatuan=findViewById(R.id.satuanbrg);
        eharga=findViewById(R.id.hargabrg);

        dbr= FirebaseDatabase.getInstance().getReference();
        modelBarang=new ModelBarang();

        Bundle bundle=getIntent().getExtras();
        ekode.setText(bundle.getString("kodene"));
        enama.setText(bundle.getString("namane"));
        esatuan.setText(bundle.getString("satuane"));
        eharga.setText(bundle.getString("hargane"));
        updateButton=findViewById(R.id.tombolupdate);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelBarang.setKode(ekode.getText().toString());
                modelBarang.setNama_brg(enama.getText().toString());
                modelBarang.setSatuan(esatuan.getText().toString());
                modelBarang.setHarga(Integer.parseInt(eharga.getText().toString()));

                String kunci=(bundle.getString("kuncine"));
                dbr.child("StokBarang").child(kunci).setValue(modelBarang).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(UpdateDataBarang.this, "Update Sukses",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}