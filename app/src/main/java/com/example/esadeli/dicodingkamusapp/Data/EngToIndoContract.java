package com.example.esadeli.dicodingkamusapp.Data;

import android.provider.BaseColumns;

/**
 * Created by esadeli on 29/07/18.
 *
 * Database Contract of English Dictionary
 */

public class EngToIndoContract {

    static String ENG_TABLE_NAME = "table_eng_to_ind";

    static final class EngToIndColumns implements BaseColumns {

        // Kata
        static String KATA_INGGRIS = "kata";
        // Arti kata
        static String ARTI_KATA_INGGRIS = "artikata";

    }
}