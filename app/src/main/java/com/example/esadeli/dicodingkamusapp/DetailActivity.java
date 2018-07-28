package com.example.esadeli.dicodingkamusapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.esadeli.dicodingkamusapp.R;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_KATA = "extra-kata";
    public static final String EXTRA_ARTI = "extra-arti";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView detailKataTV = findViewById(R.id.kata_detailTV);
        TextView detailArtiTV = findViewById(R.id.arti_kata_detailTV);

        String kata = getIntent().getStringExtra(EXTRA_KATA);
        String arti = getIntent().getStringExtra(EXTRA_ARTI);

        detailKataTV.setText(kata);
        detailArtiTV.setText(arti);
    }
}
