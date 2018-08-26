package com.example.carinacunha.inventoryapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.carinacunha.inventoryapp.BookStoreDatabase.DatabaseHelper;
import com.example.carinacunha.inventoryapp.BookStoreDatabase.DatabaseContract.BookEntry;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
        insertBook();
        queryDatabase();
    }

    /**
     * Here we will read from the database
     * All of this will need rewriting for phase 2
     */
    private void queryDatabase() {

        SQLiteDatabase database = dbHelper.getReadableDatabase();
        String[] project = {
                BookEntry._ID,
                BookEntry.COLUMN_NAME,
                BookEntry.COLUMN_GENRE,
                BookEntry.COLUMN_QUANTITY,
                BookEntry.COLUMN_PRICE,
                BookEntry.COLUMN_SUPPLIER,
                BookEntry.COLUMN_SUPPLIER_PHONE
        };

        String bookSelection = BookEntry._ID + "=?";

        String[] bookSelectionArg = {"1"};

        Cursor cursor = database.query(
                BookEntry.TABLE_NAME,
                project,
                bookSelection,
                bookSelectionArg,
                null,
                null,
                null);

        cursor.moveToNext();
        int nameColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_NAME);
        String cursorString = cursor.getString(nameColumnIndex);
        Log.i(LOG_TAG, cursorString);
        cursor.close();
    }

    /**
     * Here we will get the information from the layout edition and insert the book into the database
     * I inserted some dummy data, but the information should come from the layout edition in phase 2
     * This will insert the data in the database creating a new row
     * With the provided fields and information
     */
    private void insertBook() {
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        String bookName = "The Island";
        String bookGenre = "Horror";
        int bookQuantity = 1;
        int bookPrice = 10;
        String bookSupplier = "Hot Key Books";
        String supplierPhoneString = "+351 914578360";

        ContentValues insertIntoDatabase = new ContentValues();
        insertIntoDatabase.put(BookEntry.COLUMN_NAME, bookName);
        insertIntoDatabase.put(BookEntry.COLUMN_GENRE, bookGenre);
        insertIntoDatabase.put(BookEntry.COLUMN_QUANTITY, bookQuantity);
        insertIntoDatabase.put(BookEntry.COLUMN_PRICE, bookPrice);
        insertIntoDatabase.put(BookEntry.COLUMN_SUPPLIER, bookSupplier);
        insertIntoDatabase.put(BookEntry.COLUMN_SUPPLIER_PHONE, supplierPhoneString);

        long newBook = database.insert(BookEntry.TABLE_NAME, null, insertIntoDatabase);
    }
}