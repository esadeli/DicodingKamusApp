package com.example.esadeli.dicodingkamusapp.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.example.esadeli.dicodingkamusapp.Data.EngToIndoContract.ENG_TABLE_NAME;
import static com.example.esadeli.dicodingkamusapp.Data.EngToIndoContract.EngToIndColumns.ARTI_KATA;
import static com.example.esadeli.dicodingkamusapp.Data.EngToIndoContract.EngToIndColumns.KATA;
import static com.example.esadeli.dicodingkamusapp.Data.IndoToEngContract.IND_TABLE_NAME;

/**
 * Created by esadeli on 29/07/18.
 *
 * Class for create and update table
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "dbkamus";

    private static final int DATABASE_VERSION = 1;

    private static String CREATE_TABLE_ENG_TO_IND = "create table " + ENG_TABLE_NAME +
            " (" + _ID + " integer primary key autoincrement, " +
            KATA + " text not null, " +
            ARTI_KATA + " text not null);";

    private static String CREATE_TABLE_IND_TO_ENG = "create table " + IND_TABLE_NAME +
            " (" + _ID + " integer primary key autoincrement, " +
            KATA + " text not null, " +
            ARTI_KATA + " text not null);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_ENG_TO_IND);
        db.execSQL(CREATE_TABLE_IND_TO_ENG);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + ENG_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + IND_TABLE_NAME);
        onCreate(db);
    }
}
