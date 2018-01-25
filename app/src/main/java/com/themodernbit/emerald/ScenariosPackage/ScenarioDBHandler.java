package com.themodernbit.emerald.ScenariosPackage;

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

    private static final String STATEMENTS_TABLE_NAME = "statementsTable";
    private static final String COLUMN_STATEMENT_ID  = "statementID";
    private static final String COLUMN_STATEMENT_SID = "statementSID";
    private static final String COLUMN_STATEMENT = "statementColumn";
    private static final String COLUMN_ARABIC_STATEMENT = "arabicStatementColumn";
    private static final String COLUMN_QID_FK = "statementID_FK";
    private static final String COLUMN_STATEMENT_ISCOMPLETE = "statementIsComplete";



    public int ScenarioCount = 0;

    public ScenarioDBHandler(Context context, String DBname, SQLiteDatabase.CursorFactory theFactory, int version){
        super(context,DBname,theFactory,version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String CREATE_TABLE_SCENARIOS = "CREATE TABLE " + SCENARIOS_TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY UNIQUE, " +
                    COLUMN_NAME + " TEXT, " + COLUMN_ARABIC_NAME + " TEXT, " + COLUMN_COMPLETENESS + " BOOLEAN )";


        String CREATE_TABLE_STATEMENTS = "CREATE TABLE " + STATEMENTS_TABLE_NAME + " (" + COLUMN_STATEMENT_ID + " INTEGER PRIMARY KEY UNIQUE, " +
                COLUMN_STATEMENT_SID + " INTEGER, " + COLUMN_STATEMENT + " TEXT, " + COLUMN_ARABIC_STATEMENT +
                " TEXT, " + COLUMN_QID_FK + " INTEGER, " + COLUMN_STATEMENT_ISCOMPLETE + " BOOLEAN )";

            sqLiteDatabase.execSQL(CREATE_TABLE_SCENARIOS);
            sqLiteDatabase.execSQL(CREATE_TABLE_STATEMENTS);

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
    public void insertStatement(Statements theStatement){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();

        args.put(COLUMN_STATEMENT_ID, theStatement.getqID());
        args.put(COLUMN_STATEMENT_SID, theStatement.getsID());
        args.put(COLUMN_STATEMENT, theStatement.getStatement());
        args.put(COLUMN_ARABIC_STATEMENT, theStatement.getArabicStatement());
        args.put(COLUMN_QID_FK, theStatement.getqID_FK());
        args.put(COLUMN_STATEMENT_ISCOMPLETE, theStatement.getIsComplete());

        db.insert(STATEMENTS_TABLE_NAME, null, args);

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


    public Statements[] getRelatedStatements(int sidValue){
        Statements[] theStatement;
        int Count;

        SQLiteDatabase db = this.getReadableDatabase();
        String theQuery = "SELECT * FROM " + STATEMENTS_TABLE_NAME + " AS A , " + SCENARIOS_TABLE_NAME + " AS B WHERE A."
                + COLUMN_STATEMENT_SID + " = B." + COLUMN_ID + " AND A." + COLUMN_STATEMENT_SID + " = " + sidValue + " AND A." +
                COLUMN_QID_FK + " = -1";

        Cursor theCursor = db.rawQuery(theQuery, null);
        Count = theCursor.getCount();

        theStatement = new Statements[Count];
        theCursor.moveToFirst();

        for(int i = 0; i < Count ; i++){

            theStatement[i] = new Statements(theCursor.getInt(0), theCursor.getInt(1), theCursor.getString(2), theCursor.getString(3));
            theCursor.moveToNext();
        }

        theCursor.close();
        return theStatement;

    }

    public Statements[] getRelatedAnswers(int qID_FK){
        Statements[] theStatement;
        int Count;

        SQLiteDatabase db = this.getReadableDatabase();
        String theQuery = "SELECT * FROM " + STATEMENTS_TABLE_NAME + " AS A , " + STATEMENTS_TABLE_NAME + " AS B WHERE A."
                + COLUMN_QID_FK + " = B." + COLUMN_STATEMENT_ID + " AND B." + COLUMN_STATEMENT_ID + " = " + qID_FK;

        Cursor theCursor = db.rawQuery(theQuery, null);
        Count = theCursor.getCount();

        theStatement = new Statements[Count];
        theCursor.moveToFirst();

        for(int i = 0; i < Count ; i++){

            theStatement[i] = new Statements(theCursor.getInt(0), theCursor.getInt(1), theCursor.getString(2), theCursor.getString(3));
            theCursor.moveToNext();
        }

        theCursor.close();
        return theStatement;

    }


    public void FillDB(){


        Scenario theScenario = new Scenario(0, "Medical", "طبي");
        insertScenario(theScenario);

        theScenario = new Scenario(1,"Job Interview", "مقابلة عمل");
        insertScenario(theScenario);

        theScenario = new Scenario(2, "Restaurant", "مطعم");
        insertScenario(theScenario);

        theScenario = new Scenario(3, "Transportation", "وسائل النقل");
        insertScenario(theScenario);

        Statements theStatement = new Statements(0,1, "Hello, How are you?", "مرحبا كيف حالك؟");
        insertStatement(theStatement);

        theStatement = new Statements(1,1, "Why do you think we should hire you?", "لماذا تعتقد اننا يجب ان نوظفك؟");
        insertStatement(theStatement);

        theStatement = new Statements(2,0, "Does this hurt?","هل هذا يؤلم؟" );
        insertStatement(theStatement);

        theStatement = new Statements(3,0, "What symptoms have you had since your last visit?","ما هي الأعراض التي لديك منذ زيارتك الأخيرة؟" );
        insertStatement(theStatement);

        theStatement = new Statements(4,0, "Have your symptoms changed in any way since your last visit?","هل تغيرت أعراضك بأي شكل من الأشكال منذ زيارتك الأخيرة؟" );
        insertStatement(theStatement);

        theStatement = new Statements(5,1, "Because, i think my experience is a good addition to your team.","لأني اعتقد ان تجربتي هي أضافه جيده لفريقك." , 1);
        insertStatement(theStatement);

        theStatement = new Statements(6,1, "I think that my experience in the industry and my ability to work autonomously make me a good match for this position."
                ,"اعتقد ان تجربتي في هذا المجال وقدرتي علي العمل بشكل مستقل يجعلني مرشح جيد لهذا المنصب." , 1);
        insertStatement(theStatement);

        theStatement = new Statements(7,1, "Your company provides many services that I have had experience with, in a variety of capacities. I believe that my familiarity with the industry would make me a good fit for this position."
                ,"شركتكم توفر العديد من الخدمات التي كان لي خبره فيها. واعتقد ان تجربتي في هذا المجال من شانه ان يجعلني مناسب لهذا المنصب.\n" , 1);
        insertStatement(theStatement);

        theStatement = new Statements(8,1, "I have the motivation, experience, and superior communication skills to be an asset to your company.",
                "لدي الحافز ، والخبرة ، والقدرة علي التواصل بتفوق لأكون أضافه مهمة للشركة." , 1);
        insertStatement(theStatement);

        theStatement = new Statements(9,1, "Because, i think my experience is a good addition to your team.",
                "لأني اعتقد ان تجربتي هي أضافه جيده لفريقك." , 1);
        insertStatement(theStatement);


    }




}
