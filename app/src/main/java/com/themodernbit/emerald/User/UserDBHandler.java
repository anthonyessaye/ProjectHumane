package com.themodernbit.emerald.User;

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
    private static final String DATABASE_NAME = "UserDB.db";
    private static final String TABLE_NAME = "userTable";
    private static final String COLUMN_ID = "userID";
    private static final String COLUMN_NAME = "UserName";
    private static final String COLUMN_PHONE = "UserPhoneNumber";
    private static final String COLUMN_PROGRESS = "userProgress";
    private static final String COLUMN_LANGUAGE = "userLanguage";


    public UserDBHandler (Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, " + COLUMN_NAME + " TEXT, " + COLUMN_PHONE + " TEXT, " + COLUMN_PROGRESS +
                " TEXT, " + COLUMN_LANGUAGE + " TEXT )";

        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}


    public String loadHandler()  {
            String result = "";

            String query = "Select * FROM " + TABLE_NAME;
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(query, null);

            while (cursor.moveToNext()) {

                int result_0 = cursor.getInt(0);
                String result_1 = cursor.getString(1);
                result += String.valueOf(result_0) + " " + result_1 +

                        System.getProperty("line.separator");

            }

            cursor.close();
            db.close();

            return result;
        }


    public void addHandler(UserClass user) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getUserName());
        values.put(COLUMN_PHONE, user.getUserPhoneNumber());
        values.put(COLUMN_LANGUAGE, user.getUserPreferredLanguage());
        values.put(COLUMN_PROGRESS, user.getUserProgress());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);


    }

    public UserClass findHandler(String userName, String userPhone) {

        String query = "Select * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " = " + "'" + userName + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        UserClass user = new UserClass();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            user.setUserName(cursor.getString(1));
            cursor.close();

        } else {
            user = null;
        }

        db.close();
        return user;
    }


        public boolean deleteHandler(int ID){
            Boolean result = false;

            String query = "Select * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "= '" + String.valueOf(ID) + "'";
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(query, null);
            UserClass user = new UserClass();

            if (cursor.moveToFirst()) {

                db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[] {
                    String.valueOf(1)
                });

                cursor.close();
                result = true;
            }

            db.close();
            return result;
        }

    public boolean updateHandler( String name, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_NAME, name);
        args.put(COLUMN_PHONE, phone);
        args.put(COLUMN_LANGUAGE, "en");

            return db.update(TABLE_NAME, args, COLUMN_ID + "=" + 1, null) > 0;
        }
}


