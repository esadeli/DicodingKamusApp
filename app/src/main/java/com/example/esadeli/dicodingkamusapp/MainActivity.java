package com.example.esadeli.dicodingkamusapp;

import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.esadeli.dicodingkamusapp.Data.KamusHelper;
import com.example.esadeli.dicodingkamusapp.Model.KamusSederhana;
import com.example.esadeli.dicodingkamusapp.Preferences.FirstRunPreferences;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button engtToIndBtn = findViewById(R.id.eng_to_ind_btn);
        engtToIndBtn.setOnClickListener(this);

        Button indToEngBtn = findViewById(R.id.ind_to_eng_btn);
        indToEngBtn.setOnClickListener(this);

        new KamusAsyncTask().execute();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.eng_to_ind_btn){
            Intent engIntent =
                    new Intent(MainActivity.this, EngToIndActivity.class);
            startActivity(engIntent);
        }else if(v.getId()==R.id.ind_to_eng_btn){
            Intent indIntent =
                    new Intent(MainActivity.this, IndToEngActivity.class);
            startActivity(indIntent);
        }
    }

    private class KamusAsyncTask extends AsyncTask<Void,Void,Void> {

        KamusHelper kamusHelper;
        FirstRunPreferences firstRunPreferences;

        @Override
        protected void onPreExecute() {

            kamusHelper = new KamusHelper(MainActivity.this);
            firstRunPreferences = new FirstRunPreferences(MainActivity.this);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            boolean checkFirstRun = firstRunPreferences.getFirstRun();

            if(checkFirstRun){

                ArrayList<KamusSederhana> kamusInggris = preLoadEngRaw();
                ArrayList<KamusSederhana> kamusIndonesia = preLoadIndRaw();

                kamusHelper.open();

                kamusHelper.beginTransaction();

                try {
                    for (KamusSederhana kataInggris : kamusInggris) {
                        kamusHelper.insertEngTransaction(kataInggris);
                    }

                    for (KamusSederhana kataIndonesia : kamusIndonesia) {
                        kamusHelper.insertIndTransaction(kataIndonesia);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


                kamusHelper.setTransactionSuccess();
                kamusHelper.endTransaction();

                kamusHelper.close();

            }else{
                try{
                    this.wait(2000);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    public ArrayList<KamusSederhana> preLoadEngRaw() {
        ArrayList<KamusSederhana> kamusSederhanas = new ArrayList<>();
        String line;
        BufferedReader reader;
        try {
            Resources res = getResources();
            InputStream raw_dict = res.openRawResource(R.raw.english_indonesia);

            reader = new BufferedReader(new InputStreamReader(raw_dict));

            do {
                line = reader.readLine();
                if(line!=null){
                    String[] splitstr = line.split("\t");

                    KamusSederhana kamusInggris;

                    kamusInggris = new KamusSederhana(splitstr[0], splitstr[1]);
                    kamusSederhanas.add(kamusInggris);
                }
            } while (line != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kamusSederhanas;
    }

    public ArrayList<KamusSederhana> preLoadIndRaw() {
        ArrayList<KamusSederhana> kamusSederhanas = new ArrayList<>();
        String line;
        BufferedReader reader;
        try {
            Resources res = getResources();
            InputStream raw_dict = res.openRawResource(R.raw.indonesia_english);

            reader = new BufferedReader(new InputStreamReader(raw_dict));

            do {
                line = reader.readLine();

                if(line!=null){
                    String[] splitstr = line.split("\t");

                    KamusSederhana kamusIndonesia;

                    kamusIndonesia = new KamusSederhana(splitstr[0], splitstr[1]);
                    kamusSederhanas.add(kamusIndonesia);
                }
            } while (line != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kamusSederhanas;
    }
}
