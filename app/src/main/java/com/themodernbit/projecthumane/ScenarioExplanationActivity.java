package com.themodernbit.projecthumane;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.themodernbit.projecthumane.ScenariosPackage.Statements;

public class ScenarioExplanationActivity extends AppCompatActivity {

    private static String KEY_TRANSFERFIRSTDATA = "FirstTransfer";
    private static String KEY_TRANSFERSECONDDATA = "SecondTransfer";

    private String QuestionStatements;
    private Statements[] AnswerStatments;

    private TextView QuestionText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenario_explanation);

        Intent PreviousIntent = getIntent();
        QuestionStatements = PreviousIntent.getStringExtra(KEY_TRANSFERFIRSTDATA);
        //AnswerStatments = (Statements[]) PreviousIntent.getParcelableArrayExtra(KEY_TRANSFERSECONDDATA);

        QuestionText = (TextView) findViewById(R.id.TextViewQuestions) ;
        QuestionText.setText(QuestionStatements);

    }


}
