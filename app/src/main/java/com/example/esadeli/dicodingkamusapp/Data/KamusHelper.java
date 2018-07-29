package com.example.esadeli.dicodingkamusapp.Data;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.esadeli.dicodingkamusapp.Model.KamusSederhana;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.esadeli.dicodingkamusapp.Data.EngToIndoContract.ENG_TABLE_NAME;
import static com.example.esadeli.dicodingkamusapp.Data.EngToIndoContract.EngToIndColumns.ARTI_KATA_INGGRIS;
import static com.example.esadeli.dicodingkamusapp.Data.EngToIndoContract.EngToIndColumns.KATA_INGGRIS;
import static com.example.esadeli.dicodingkamusapp.Data.IndoToEngContract.IND_TABLE_NAME;
import static com.example.esadeli.dicodingkamusapp.Data.IndoToEngContract.IndoToEngColumns.ARTI_KATA_INDONESIA;
import static com.example.esadeli.dicodingkamusapp.Data.IndoToEngContract.IndoToEngColumns.KATA_INDONESIA;

/**
 * Created by esadeli on 29/07/18.
 *
 * Class for CRUD operation
 */

public class KamusHelper {

    private Context context;
    private DatabaseHelper dataBaseHelper;

    private SQLiteDatabase database;

    public KamusHelper(Context context) {
        this.context = context;
    }

    public KamusHelper open() throws SQLException {
        dataBaseHelper = new DatabaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public KamusHelper openQuery() throws SQLException {
        dataBaseHelper = new DatabaseHelper(context);
        database = dataBaseHelper.getReadableDatabase();
        return this;
    }


    public void close() {
        dataBaseHelper.close();
    }


    public ArrayList<KamusSederhana> getDataEngByKata(String kata) {
        Cursor cursor = database.query(
                ENG_TABLE_NAME,
                null,
                KATA_INGGRIS + " LIKE ?", new String[]{kata},
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();
        ArrayList<KamusSederhana> arrayList = new ArrayList<>();
        KamusSederhana kamusInggris;
        if (cursor.getCount() > 0) {
            do {
                kamusInggris = new KamusSederhana();
                kamusInggris.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                kamusInggris.setKata(cursor.getString(cursor.getColumnIndexOrThrow(KATA_INGGRIS)));
                kamusInggris.setArti(cursor.getString(cursor.getColumnIndexOrThrow(ARTI_KATA_INGGRIS)));

                arrayList.add(kamusInggris);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }


    public ArrayList<KamusSederhana> getDataIndByKata(String kata) {
        Cursor cursor = database.query(
                IND_TABLE_NAME,
                null,
                KATA_INDONESIA + " LIKE ?", new String[]{kata},
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();
        ArrayList<KamusSederhana> arrayList = new ArrayList<>();
        KamusSederhana kamusIndonesia;
        if (cursor.getCount() > 0) {
            do {
                kamusIndonesia = new KamusSederhana();
                kamusIndonesia.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                kamusIndonesia.setKata(cursor.getString(
                        cursor.getColumnIndexOrThrow(KATA_INDONESIA)));
                kamusIndonesia.setArti(cursor.getString(
                        cursor.getColumnIndexOrThrow(ARTI_KATA_INDONESIA)));

                arrayList.add(kamusIndonesia);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public ArrayList<KamusSederhana> getAllEngData() {
        Cursor cursor = database.query(ENG_TABLE_NAME,
                null,
                null,
                null,
                null,
                null, _ID + " ASC",
                null);
        cursor.moveToFirst();
        ArrayList<KamusSederhana> arrayList = new ArrayList<>();
        KamusSederhana kamusEnglish;
        if (cursor.getCount() > 0) {
            do {
                kamusEnglish = new KamusSederhana();
                kamusEnglish.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                kamusEnglish.setKata(cursor.getString(cursor.getColumnIndexOrThrow(KATA_INGGRIS)));
                kamusEnglish.setArti(cursor.getString(cursor.getColumnIndexOrThrow(ARTI_KATA_INGGRIS)));


                arrayList.add(kamusEnglish);
                cursor.moveToNext();


            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public ArrayList<KamusSederhana> getAllIndData() {
        Cursor cursor = database.query(IND_TABLE_NAME,
                null,
                null,
                null,
                null,
                null, _ID + " ASC",
                null);
        cursor.moveToFirst();
        ArrayList<KamusSederhana> arrayList = new ArrayList<>();
        KamusSederhana kamusIndonesian;
        if (cursor.getCount() > 0) {
            do {
                kamusIndonesian = new KamusSederhana();
                kamusIndonesian.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                kamusIndonesian.setKata(cursor.getString(
                        cursor.getColumnIndexOrThrow(KATA_INDONESIA)));
                kamusIndonesian.setArti(cursor.getString(
                        cursor.getColumnIndexOrThrow(ARTI_KATA_INDONESIA)));


                arrayList.add(kamusIndonesian);
                cursor.moveToNext();


            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public void beginTransaction() {
        database.beginTransaction();
    }

    public void setTransactionSuccess() {
        database.setTransactionSuccessful();
    }

    public void endTransaction() {
        database.endTransaction();
    }

    public void insertEngTransaction(KamusSederhana kamusSederhana) {
        String sql = "INSERT INTO " + ENG_TABLE_NAME + " (" + KATA_INGGRIS + ", " +
                ARTI_KATA_INGGRIS + ") VALUES (?, ?)";
        SQLiteStatement stmt = database.compileStatement(sql);

        stmt.bindString(1, kamusSederhana.getKata());
        stmt.bindString(2, kamusSederhana.getArti());

        stmt.execute();

        stmt.clearBindings();
    }

    public void insertIndTransaction(KamusSederhana kamusSederhana) {
        String sql = "INSERT INTO " + IND_TABLE_NAME + " (" + KATA_INDONESIA + ", " +
                ARTI_KATA_INDONESIA + ") VALUES (?, ?)";
        SQLiteStatement stmt = database.compileStatement(sql);

        stmt.bindString(1, kamusSederhana.getKata());
        stmt.bindString(2, kamusSederhana.getArti());

        stmt.execute();

        stmt.clearBindings();
    }

}

