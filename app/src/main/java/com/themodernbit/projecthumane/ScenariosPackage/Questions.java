package com.themodernbit.projecthumane.ScenariosPackage;

/**
 * Created by antho on 1/24/2018.
 */

public class Questions {

    private int qID;
    private int sID;
    private String Question;
    private String Answer;
    private String ArabicQuestion;
    private String ArabicAnswer;
    private int qID_FK;
    private boolean isComplete;

    public Questions(int qID, int sID, String question, String Answer, String ArabicQuestion, String ArabicAnswer){
        this.qID = qID;
        this.sID = sID;
        this.Question = question;
        this.Answer = Answer;
        this.ArabicQuestion = ArabicQuestion;
        this.ArabicAnswer = ArabicAnswer;

        this.qID_FK = -1;
        this.isComplete = false;

    }

    public int getqID() {return this.qID;}
    public int getsID() {return this.sID;}
    public String getQuestion() {return this.Question;}
    public String getAnswer() {return this.Answer;}
    public String getArabicQuestion() {return this.ArabicQuestion;}
    public String getArabicAnswer() {return this.ArabicAnswer;}


}
