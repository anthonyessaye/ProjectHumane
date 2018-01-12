package com.themodernbit.projecthumane.TagsActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.themodernbit.projecthumane.R;

public class ChosenTranslationActivity extends AppCompatActivity {

    private static String KEY_TAG_TOTRANSLATE = "Translate";
    private TextView originalWord;
    private TextView translatedWord;

    private String[] CombinedLanguages;
    private String bundledLanguage;
    //This class works after an item is chosen from list.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosen_translation);

        originalWord = (TextView) findViewById(R.id.txtViewOriginalLanguage);
        translatedWord = (TextView) findViewById(R.id.txtViewTranslatedLanguage);


        Intent theIntent = getIntent();
        bundledLanguage = theIntent.getStringExtra(KEY_TAG_TOTRANSLATE);
        CombinedLanguages = bundledLanguage.split(" ");


        originalWord.setText(originalWord.getText() + " " + CombinedLanguages[0]);
        translatedWord.setText(translatedWord.getText() + " " + CombinedLanguages[1]);





    }





}
