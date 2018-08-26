package com.example.carinacunha.inventoryapp.BookStoreDatabase;

import android.provider.BaseColumns;

public final class DatabaseContract {

    /**
     * empty constructor
     */
    private DatabaseContract() {
    }

    public static final class BookEntry implements BaseColumns {

        public final static String TABLE_NAME = "books";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME = "name";
        public final static String COLUMN_GENRE = "genre";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_PRICE = "price";

        public final static String COLUMN_SUPPLIER = "supplier";
        public final static String COLUMN_SUPPLIER_PHONE = "phone";
    }
}

