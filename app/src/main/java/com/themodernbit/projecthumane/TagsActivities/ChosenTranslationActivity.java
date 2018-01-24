package com.themodernbit.projecthumane.TagsActivities;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.themodernbit.projecthumane.R;

import java.util.Locale;

public class ChosenTranslationActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private static String KEY_TAG_TOTRANSLATE = "Translate";
    private TextView originalWord;
    private TextView translatedWord;
    private TextToSpeech tts;


    private String[] CombinedLanguages;
    private String bundledLanguage;
    //This class works after an item is chosen from list.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosen_translation);

        originalWord = (TextView) findViewById(R.id.txtViewOriginalLanguage);
        translatedWord = (TextView) findViewById(R.id.txtViewTranslatedLanguage);
        tts = new TextToSpeech(this, this);

        Intent theIntent = getIntent();
        bundledLanguage = theIntent.getStringExtra(KEY_TAG_TOTRANSLATE);
        CombinedLanguages = bundledLanguage.split(" ");


        originalWord.setText(originalWord.getText() + " " + CombinedLanguages[0]);

        for(int i = 1 ; i < CombinedLanguages.length; i++)
        translatedWord.setText(translatedWord.getText() + " " + CombinedLanguages[i]);





    }

public void onTTSClick(View view){
    String TextToSpeak = originalWord.getText().toString();
    String[] Array = new String[3];
    Array = TextToSpeak.split(" ");
    tts.speak(Array[2], TextToSpeech.QUEUE_FLUSH, null);

}


    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);
            result = tts.setPitch((float) 0.8);
            tts.setSpeechRate((float) 0.6);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {

             //   onTTSClick(View view);
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }
    }



    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
