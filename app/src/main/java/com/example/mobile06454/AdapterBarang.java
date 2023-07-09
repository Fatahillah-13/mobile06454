package com.example.mobile06454;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdapterBarang extends RecyclerView.Adapter<AdapterBarang.myViewHolder> {

    Activity activity;
    ArrayList<ModelBarang> modelBarangArrayList;

    DatabaseReference dbr;

    public AdapterBarang(Activity activity, ArrayList<ModelBarang> modelBarangArrayList) {
        this.activity = activity;
        this.modelBarangArrayList = modelBarangArrayList;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(activity);
        View view=inflater.inflate(R.layout.format_tampilan_barang,parent,false);
        return new AdapterBarang.myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.ckode.setText(modelBarangArrayList.get(holder.getAdapterPosition()).getKode());
        holder.cnama.setText(modelBarangArrayList.get(holder.getAdapterPosition()).getNama_brg());
        holder.csatuan.setText(modelBarangArrayList.get(holder.getAdapterPosition()).getSatuan());
        holder.charga.setText(String.valueOf(modelBarangArrayList.get(holder.getAdapterPosition()).getHarga()));
        holder.card01.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent=new Intent(view.getContext(),UpdateDataBarang.class);
                intent.putExtra("kuncine",modelBarangArrayList.get(holder.getAdapterPosition()).getKey());
                intent.putExtra("kodene",modelBarangArrayList.get(holder.getAdapterPosition()).getKode());
                intent.putExtra("namane",modelBarangArrayList.get(holder.getAdapterPosition()).getNama_brg());
                intent.putExtra("satuane",modelBarangArrayList.get(holder.getAdapterPosition()).getSatuan());
                intent.putExtra("hargane",String.valueOf(modelBarangArrayList.get(holder.getAdapterPosition()).getHarga()));
                view.getContext().startActivity(intent);
                return false;
            }
        });

        holder.hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder =new AlertDialog.Builder(activity);
                builder.setPositiveButton("YA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbr= FirebaseDatabase.getInstance().getReference();
                        dbr.child("StokBarang").child(modelBarangArrayList.get(holder.getAdapterPosition()).getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(activity, "Hapus Berhasil", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).setMessage("Apakah yakin data "+ modelBarangArrayList.get(holder.getAdapterPosition()).getNama_brg() +" akan dihapus ?");
            builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelBarangArrayList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView ckode, cnama, csatuan, charga;
        ImageView hapus;
        CardView card01;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            ckode=itemView.findViewById(R.id.kodebarang);
            cnama=itemView.findViewById(R.id.namabarang);
            csatuan=itemView.findViewById(R.id.satuanbarang);
            charga=itemView.findViewById(R.id.hargabarang);
            hapus=itemView.findViewById(R.id.tombolhapus);
            card01=itemView.findViewById(R.id.cardview_barang);
        }
    }
}
