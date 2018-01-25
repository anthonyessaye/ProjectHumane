package com.themodernbit.emerald.ScenariosPackage;

/**
 * Created by antho on 1/24/2018.
 */

public class Statements {

    private int qID;
    private int sID;
    private String Statement;
    private String ArabicStatement;
    private int qID_FK;
    private boolean isComplete;

    public Statements(int qID, int sID, String statement, String ArabicStatement){
        this.qID = qID;
        this.sID = sID;
        this.Statement = statement;
        this.ArabicStatement = ArabicStatement;


        this.qID_FK = -1;
        this.isComplete = false;

    }

    public Statements(int qID, int sID, String statement, String ArabicStatement, int qID_fk){
        this.qID = qID;
        this.sID = sID;
        this.Statement = statement;
        this.ArabicStatement = ArabicStatement;


        this.qID_FK = qID_fk;
        this.isComplete = false;

    }

    public int getqID() {return this.qID;}
    public int getsID() {return this.sID;}
    public String getStatement() {return this.Statement;}
    public String getArabicStatement() {return this.ArabicStatement;}
    public int getqID_FK() {return this.qID_FK;}
    public boolean getIsComplete() {return this.isComplete;}


    public void setqID(int qid) {this.qID = qid;}
    public void setsID(int sid) {this.sID = sid;}
    public void setStatement(String statement) {this.Statement = statement;}
    public void setArabicStatement(String arabicStatement) {this.ArabicStatement = arabicStatement;}
    public void setqID_FK(int qID_fk) {this.qID_FK = qID_fk;}
    public void setComplete(boolean isComplete) {this.isComplete = isComplete;}

}
