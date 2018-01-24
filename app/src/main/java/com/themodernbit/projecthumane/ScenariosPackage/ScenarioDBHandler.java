package com.themodernbit.projecthumane.ScenariosPackage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by antho on 1/23/2018.
 */

public class ScenarioDBHandler extends SQLiteOpenHelper {

    private static int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UserDB.db";
    private static final String SCENARIOS_TABLE_NAME = "scenariosTable";
    private static final String COLUMN_ID = "scenarioID";
    private static final String COLUMN_NAME = "scenarioName";
    private static final String COLUMN_ARABIC_NAME = "scenarioArabicName";
    private static final String COLUMN_COMPLETENESS = "isScenarioComplete";
    public int ScenarioCount = 0;

    public ScenarioDBHandler(Context context, String DBname, SQLiteDatabase.CursorFactory theFactory, int version){
        super(context,DBname,theFactory,version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String CREATE_TABLE_SCENARIOS = "CREATE TABLE " + SCENARIOS_TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY UNIQUE, " +
                    COLUMN_NAME + " TEXT, " + COLUMN_ARABIC_NAME + " TEXT, " + COLUMN_COMPLETENESS + " BOOLEAN )";

            sqLiteDatabase.execSQL(CREATE_TABLE_SCENARIOS);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    // This function will be used to insert each scenario manually
    public void insertScenario(Scenario theScenario){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();

        args.put(COLUMN_ID, theScenario.getScenarioID());
        args.put(COLUMN_NAME, theScenario.getScenarioName());
        args.put(COLUMN_ARABIC_NAME, theScenario.getScenarioArabicName());
        args.put(COLUMN_COMPLETENESS, theScenario.getCompleteness());

        db.insert(SCENARIOS_TABLE_NAME, null, args);

    }

    // This is a function that will aid in updating the completeness of a certain scenario.
    public void setCompleteness(int ID, boolean isCompleted){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();

        args.put(COLUMN_COMPLETENESS, isCompleted);
        db.update(SCENARIOS_TABLE_NAME , args, COLUMN_ID +" = "+ ID, null);
    }


    public Scenario[] getScenario(){
        int Count;
        Scenario[] theScenario;
        SQLiteDatabase db = this.getReadableDatabase();

        String theQuery = "SELECT * FROM " + SCENARIOS_TABLE_NAME;
        Cursor theCursor = db.rawQuery(theQuery, null);
        Count = theCursor.getCount();



        theScenario = new Scenario[Count];
        theCursor.moveToFirst();
        for(int i = 0; i < Count ; i++)
        {
            theScenario[i] = new Scenario(theCursor.getInt(0), theCursor.getString(1), theCursor.getString(2));
            theCursor.moveToNext();
        }

        theCursor.close();

        return theScenario;
    }


    public void FillScenarioDB(){


        Scenario theScenario = new Scenario(0, "Medical", "طبي");
        insertScenario(theScenario);

        theScenario = new Scenario(1,"Job Interview", "مقابلة عمل");
        insertScenario(theScenario);

        theScenario = new Scenario(2, "Restaurant", "مطعم");
        insertScenario(theScenario);

        theScenario = new Scenario(3, "Transportation", "وسائل النقل");
        insertScenario(theScenario);

    }
}
