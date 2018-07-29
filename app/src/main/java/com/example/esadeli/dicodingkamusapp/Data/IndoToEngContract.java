package com.example.esadeli.dicodingkamusapp.Data;

import android.provider.BaseColumns;

/**
 * Created by esadeli on 29/07/18.
 *
 * Database Contract of English Dictionary
 */

public class IndoToEngContract {

    static String IND_TABLE_NAME = "table_ind_to_eng";

    static final class IndoToEngColumns implements BaseColumns {

        // Kata
        static String KATA_INDONESIA = "kata";
        // Arti kata
        static String ARTI_KATA_INDONESIA = "artikata";

    }
}