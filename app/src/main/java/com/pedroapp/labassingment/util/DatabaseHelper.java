package com.pedroapp.labassingment.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contact_database";

    private static final String TABLE_NAME = "contact";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FIRSTNAME = "first_name";
    private static final String COLUMN_LASTNAME = "last_name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PHONENUMBER = "phone_number";
    private static final String COLUMN_ADDRESS = "address";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER NOT NULL CONSTRAINT contact_pk PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FIRSTNAME + " VARCHAR(20) NOT NULL, " +
                COLUMN_LASTNAME + " VARCHAR(20) NOT NULL, " +
                COLUMN_EMAIL + " VARCHAR(50) NOT NULL, " +
                COLUMN_PHONENUMBER + " VARCHAR(25) NOT NULL, " +
                COLUMN_ADDRESS + " VARCHAR(120) NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop table and then create it
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        db.execSQL(sql);
        onCreate(db);
    }

    // insert
    /**
     * add contact - insert contact into contact table
     * @param first_name
     * @param last_name
     * @param email
     * @param phone_number
     * @param address
     * @return boolean value - true (inserted) false (not inserted)
     * */
    public boolean addConctact(String first_name, String last_name, String email, String phone_number, String address) {

        // we need a writeable instance of SQLite database
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        // we need to define a content values in order to insert data into our database
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FIRSTNAME, first_name);
        contentValues.put(COLUMN_LASTNAME, last_name);
        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_PHONENUMBER, phone_number);
        contentValues.put(COLUMN_ADDRESS, address);


        // the insert method associated to SQLite database instance returns -1 if nothing is inserted
        return sqLiteDatabase.insert(TABLE_NAME, null, contentValues) != -1;
    }

    /**
     * Query database - select all the employees
     * @return cursor
     * */
    public Cursor getAllContacts() {
        // we need a readable instance of database
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    /**
     * Update employee in database
     * @param id
     * @param first_name
     * @param last_name
     * @param email
     * @param phone_number
     * @param address
     * @return boolean value - true (successful)
     * */
    public boolean updateEmployee(int id, String first_name, String last_name, String email, String phone_number, String address) {
        // we need a writeable instance of database
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FIRSTNAME, first_name);
        contentValues.put(COLUMN_LASTNAME, last_name);
        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_PHONENUMBER, phone_number);
        contentValues.put(COLUMN_ADDRESS, address);

        // update method associated to SQLite database instance returns number of rows affected
        return sqLiteDatabase.update(TABLE_NAME,
                contentValues,
                COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}) > 0;
    }

    /**
     * Delete employee from database table
     * @param id
     * @return true if is successful
     * */
    public boolean deleteContact(int id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        // the delete method associated to the SQLite database instance returns the number of rows affected
        return sqLiteDatabase.delete(TABLE_NAME,
                COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}) > 0;
    }

}