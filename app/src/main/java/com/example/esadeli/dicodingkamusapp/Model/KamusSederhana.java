package com.example.esadeli.dicodingkamusapp.Model;

/**
 * Created by esadeli on 29/07/18.
 *
 * Simple POJO Class
 */

public class KamusSederhana {

    private int id;
    private String kata;
    private String arti;

    public KamusSederhana() {

    }

    public KamusSederhana(String kata, String arti) {
        this.kata = kata;
        this.arti = arti;
    }

    public String getKata() {
        return kata;
    }

    public void setKata(String kata) {
        this.kata = kata;
    }

    public String getArti() {
        return arti;
    }

    public void setArti(String arti) {
        this.arti = arti;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
