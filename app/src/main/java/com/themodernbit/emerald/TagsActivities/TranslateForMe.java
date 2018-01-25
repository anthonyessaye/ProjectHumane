package com.themodernbit.emerald.TagsActivities;

import android.os.AsyncTask;
import android.util.Log;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

/**
 * Created by antho on 1/10/2018.
 */

public class TranslateForMe {

    private static final String API_KEY = "AIzaSyB4WNm2Gi5OFXCWOy9WrCGmOv2btWyix5M";
    private String[] WordsToTranslate;
    private Translation translation;

    public boolean isDone = false;

    public TranslateForMe(String[] TranslateArray) {
        WordsToTranslate = TranslateArray;
    }
    public TranslateForMe(String TranslateString){ WordsToTranslate= TranslateString.split("\\s");}

    public void TranslateWords() {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {

                TranslateOptions options = TranslateOptions.newBuilder()
                        .setApiKey(API_KEY)
                        .build();

                Translate translate = options.getService();


                for (int i = 0; i < WordsToTranslate.length; i++) {
                    Log.e("TAG",WordsToTranslate[i]);
                    translation = translate.translate(WordsToTranslate[i], Translate.TranslateOption.targetLanguage("ar"));
                    WordsToTranslate[i] = translation.getTranslatedText();
                }

                isDone = true;
                return null;
            }

            @Override
            protected void onPostExecute(Void result){
                isDone = true;
            }

        }.execute();

    }

    public String[] getWordsToTranslate(){
        return WordsToTranslate;
    }
}



