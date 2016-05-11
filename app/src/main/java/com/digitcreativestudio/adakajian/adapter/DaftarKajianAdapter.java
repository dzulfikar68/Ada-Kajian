package com.digitcreativestudio.adakajian.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.digitcreativestudio.adakajian.R;
import com.digitcreativestudio.adakajian.entity.Kajian;


import java.util.ArrayList;

/**
 * Created by ADIK on 26/04/2016.
 */
public class DaftarKajianAdapter extends RecyclerView.Adapter<DaftarKajianAdapter.MyViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Kajian kajian);
    }

    private ArrayList<Kajian> daftar_kajian;
    private final OnItemClickListener listener;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView judul_kajian, ustad_kajian;

        public MyViewHolder(View view) {
            super(view);
            judul_kajian = (TextView) view.findViewById(R.id.judul_kajian);
            ustad_kajian = (TextView) view.findViewById(R.id.ustad_kajian);
        }

        public void bind(final Kajian item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }


    public DaftarKajianAdapter(Context context, ArrayList<Kajian> daftar_kajian, OnItemClickListener listener) {
        this.context = context;
        this.daftar_kajian = daftar_kajian;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_kajian, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bind(daftar_kajian.get(position), listener);
        Kajian kajian = daftar_kajian.get(position);
        holder.judul_kajian.setText(kajian.getJudul_kajian());
        holder.ustad_kajian.setText(kajian.getUstad_kajian());
    }

    @Override
    public int getItemCount() {
        return daftar_kajian.size();
    }
}
