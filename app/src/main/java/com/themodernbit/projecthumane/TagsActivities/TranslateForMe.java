package com.themodernbit.projecthumane.TagsActivities;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import com.google.auth.Credentials;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import com.microsoft.projectoxford.vision.contract.Word;

/**
 * Created by antho on 1/10/2018.
 */

public class TranslateForMe {

    private static final String API_KEY = "AIzaSyDg41JnK2PZGWODyWBpzDGyRySP-kCNisk";
    private String[] WordsToTranslate;
    private Translation translation;

    public boolean isDone = false;

    public TranslateForMe(String[] TranslateArray) {
        WordsToTranslate = TranslateArray;
    }


    public void TranslateWords() {
        Log.e("TAG","Translation Has Started");
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {

                Translate translate = TranslateOptions.newBuilder().setApiKey(API_KEY).build().getService();


                for (int i = 0; i < WordsToTranslate.length; i++) {
                    translation = translate.translate(WordsToTranslate[i], Translate.TranslateOption.targetLanguage("ar"));
                    WordsToTranslate[i] = translation.getTranslatedText();



                }


                return null;
            }


            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Log.e("TAG","Translation Has Ended");
                isDone = true;
            }
        }.execute();

    }

    public String[] getWordsToTranslate(){
        return WordsToTranslate;
    }
}



