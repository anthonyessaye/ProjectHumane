package com.themodernbit.projecthumane.TagsActivities;

import android.os.AsyncTask;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import com.microsoft.projectoxford.vision.contract.Word;

/**
 * Created by antho on 1/10/2018.
 */

public class TranslateForMe {

    private static final String API_KEY = "AIzaSyAxvtn7mLOHkZrx5dAvL5fg9cpcqLSqpMk";
    private String[] WordsToTranslate;
    private Translation translation;

    public boolean isDone = false;

    public TranslateForMe(String[] TranslateArray) {
        WordsToTranslate = TranslateArray;
    }


    public void TranslateWords() {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {

                TranslateOptions options = TranslateOptions.newBuilder()
                        .setApiKey(API_KEY)
                        .build();

                Translate translate = options.getService();


                for (int i = 0; i < WordsToTranslate.length; i++) {
                    translation = translate.translate(WordsToTranslate[i], Translate.TranslateOption.targetLanguage("ar"));
                    WordsToTranslate[i] = translation.getTranslatedText();
                }

                isDone = true;
                return null;
            }

        }.execute();

    }

    public String[] getWordsToTranslate(){
        return WordsToTranslate;
    }
}



