package com.example.mobile06454;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterBarang extends RecyclerView.Adapter<AdapterBarang.myViewHolder> {

    Activity activity;
    ArrayList<ModelBarang> modelBarangArrayList;

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
        holder.ckode.setText(modelBarangArrayList.get(position).getKode());
        holder.cnama.setText(modelBarangArrayList.get(position).getNama_brg());
        holder.csatuan.setText(modelBarangArrayList.get(position).getSatuan());
        holder.charga.setText(String.valueOf(modelBarangArrayList.get(position).getHarga()));
    }

    @Override
    public int getItemCount() {
        return modelBarangArrayList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView ckode, cnama, csatuan, charga;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            ckode=itemView.findViewById(R.id.kodebarang);
            cnama=itemView.findViewById(R.id.namabarang);
            csatuan=itemView.findViewById(R.id.satuanbarang);
            charga=itemView.findViewById(R.id.hargabarang);
        }
    }
}
