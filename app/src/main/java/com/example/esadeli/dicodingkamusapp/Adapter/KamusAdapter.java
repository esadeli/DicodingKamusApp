package com.example.esadeli.dicodingkamusapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.esadeli.dicodingkamusapp.DetailActivity;
import com.example.esadeli.dicodingkamusapp.Model.KamusSederhana;
import com.example.esadeli.dicodingkamusapp.R;

import java.util.ArrayList;

/**
 * Created by esadeli on 28/07/18.
 *
 * Extension of RecyclerView.Adapter
 */

public class KamusAdapter extends RecyclerView.Adapter<KamusAdapter.KamusHolder> {

    private ArrayList<KamusSederhana> arrayList = new ArrayList<>();

    private Context mContext;

    public KamusAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void addItem(ArrayList<KamusSederhana> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @Override
    public KamusHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kamus_item, parent, false);
        return new KamusHolder(view);
    }

    @Override
    public void onBindViewHolder(KamusHolder holder, int position) {

        final String detailKata = arrayList.get(position).getKata();
        final String detailArti = arrayList.get(position).getArti();

        holder.textViewKata.setText(detailKata);
        holder.kamusDetailHolder.setOnClickListener(new KamusOnItemClickListener(position, new KamusOnItemClickListener.OnKamusItemClick() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent detailIntent = new Intent(mContext, DetailActivity.class);
                detailIntent.putExtra(DetailActivity.EXTRA_KATA,detailKata);
                detailIntent.putExtra(DetailActivity.EXTRA_ARTI,detailArti);
                mContext.startActivity(detailIntent);
            }
        }));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class KamusHolder extends RecyclerView.ViewHolder {
        private TextView textViewKata;
        private RelativeLayout kamusDetailHolder;

        public KamusHolder(View itemView) {
            super(itemView);

            textViewKata = itemView.findViewById(R.id.txt_kata);
            kamusDetailHolder = itemView.findViewById(R.id.kamus_holder);
        }
    }
}

