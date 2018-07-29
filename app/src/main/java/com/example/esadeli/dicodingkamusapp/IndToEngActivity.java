package com.example.esadeli.dicodingkamusapp;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;

import com.example.esadeli.dicodingkamusapp.Adapter.KamusAdapter;
import com.example.esadeli.dicodingkamusapp.Data.KamusHelper;
import com.example.esadeli.dicodingkamusapp.Model.KamusSederhana;

import java.util.ArrayList;

public class IndToEngActivity extends AppCompatActivity {

    //Set up SQLiteOpenHelper
    KamusHelper kamusHelper = new KamusHelper(this);

    //Set Up Adapter for the RecyclerView
    KamusAdapter kamusAdapter = new KamusAdapter(IndToEngActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamus);

        //Set up RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        // Set Up Layout Manager for the RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        // Set Up Item Divider for the RecyclerView
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(),layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setAdapter(kamusAdapter);

        // Open the SQLiteOpenHelper
        kamusHelper.openQuery();

        //Create an instance of ArrayList
        ArrayList<KamusSederhana> kamusSederhanas = kamusHelper.getAllIndData();

        //Close the SQLiteOpenHelper
        kamusHelper.close();

        //Connecting the RecyclerView adapter with the ArrayList
        kamusAdapter.addItem(kamusSederhanas);

    }

    @Override
    protected void onResume() {
        super.onResume();

        // Open the SQLiteOpenHelper
        kamusHelper.openQuery();

        //Create an instance of ArrayList
        ArrayList<KamusSederhana> kamusSederhanas = kamusHelper.getAllIndData();

        //Close the SQLiteOpenHelper
        kamusHelper.close();

        //Connecting the RecyclerView adapter with the ArrayList
        kamusAdapter.addItem(kamusSederhanas);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(kamusHelper!=null){
            kamusHelper.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu,menu);

        SearchView searchView =
                (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.search));
        searchView.setQueryHint(getResources().getString(R.string.cari));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                // Open the SQLiteOpenHelper
                kamusHelper.openQuery();

                //Create an instance of ArrayList
                ArrayList<KamusSederhana> kamusQuery = kamusHelper.getDataIndByKata(newText);

                //Close the SQLiteOpenHelper
                kamusHelper.close();

                //Connecting the RecyclerView adapter with the ArrayList
                kamusAdapter.addItem(kamusQuery);

                return true;
            }
        });

        return true;
    }
}
