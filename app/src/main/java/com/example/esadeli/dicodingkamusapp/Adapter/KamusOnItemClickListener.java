package com.example.esadeli.dicodingkamusapp.Adapter;

import android.view.View;

/**
 * Created by esadeli on 29/07/18.
 *
 * Customized on click listener
 */

public class KamusOnItemClickListener implements View.OnClickListener {

    private int position;
    private OnKamusItemClick onItemClickCallback;

    public KamusOnItemClickListener(int position, OnKamusItemClick onItemClickCallback) {
        this.position = position;
        this.onItemClickCallback = onItemClickCallback;
    }
    @Override
    public void onClick(View view) {
        onItemClickCallback.onItemClicked(view, position);
    }

    public interface OnKamusItemClick {
        void onItemClicked(View view, int position);
    }
}

