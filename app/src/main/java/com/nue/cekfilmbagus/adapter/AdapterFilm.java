package com.nue.cekfilmbagus.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nue.cekfilmbagus.MainActivity;
import com.nue.cekfilmbagus.R;

import java.util.ArrayList;
import java.util.HashMap;

public class AdapterFilm extends RecyclerView.Adapter<AdapterFilm.ViewHolder>{

    Context context;
    ArrayList<HashMap<String, String>> list_data;

    public AdapterFilm(MainActivity mainActivity, ArrayList<HashMap<String, String>> list_data) {
        this.context = mainActivity;
        this.list_data = list_data;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.linear_rec_main, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String link = "https://image.tmdb.org/t/p/w500";
        String url = link+list_data.get(position).get("gambar");
        String judul = list_data.get(position).get("judul");
        String keterangan = list_data.get(position).get("keterangan");
        String tanggal = list_data.get(position).get("tanggal");

        holder.txtJudul.setText(list_data.get(position).get("judul"));
        holder.txtKeterangan.setText(keterangan);
        holder.txtTanggal.setText(tanggal);
        Glide.with(context).load(url).into(holder.gbrPriview);

    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtJudul,txtKeterangan,txtTanggal;
        ImageView gbrPriview;


        public ViewHolder(View itemView) {
            super(itemView);

            txtJudul = itemView.findViewById(R.id.txtRecMainJudul);
            txtTanggal=itemView.findViewById(R.id.txtRecMainTgl);
            txtKeterangan=itemView.findViewById(R.id.txtRecMainKeterangan);
            gbrPriview=itemView.findViewById(R.id.gbrRecMainPriview);

        }
    }


}
