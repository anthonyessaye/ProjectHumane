package com.themodernbit.projecthumane.User;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by antho on 1/15/2018.
 */

public class UserDBHandler extends SQLiteOpenHelper {

    private static int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UserDB";
    private static final String TABLE_NAME = "userTable";
    private static final String COLUMN_ID = "userID";
    private static final String COLUMN_NAME = "UserName";
    private static final String COLUMN_PHONE = "UserPhoneNumber";


    public UserDBHandler (Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE" + TABLE_NAME + "(" + COLUMN_ID +
                "INTEGER PRIMARY KEY," + COLUMN_NAME + "TEXT," + COLUMN_PHONE + "TEXT )";

        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        public String loadHandler() {}
        public void addHandler(UserClass User) {}
        public UserClass findHandler(String userName, String userPhone) {}
        public boolean deleteHandler(int ID){}

        public boolean updateHandler(int ID, String name) {}


    }
}
