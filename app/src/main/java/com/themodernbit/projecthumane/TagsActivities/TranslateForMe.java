package com.themodernbit.projecthumane.TagsActivities;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import com.google.auth.Credentials;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import com.memetix.mst.language.Language;
import com.microsoft.projectoxford.vision.contract.Word;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by antho on 1/10/2018.
 */

public class TranslateForMe {

    private static final String API_KEY = "AIzaSyDiZRkm-RefV1GsMbiOOlRNsPJOj-BoKMI";
    private String[] WordsToTranslate;
    private Translation translation;

    private static String subscriptionKey = "a5f63baeeb9947ee9fbed3fda6c70770";
    private static String  host = "https://api.microsofttranslator.com";
    private static  String path = "/V2/Http.svc/Translate";
    private static String target = "fr-fr";

    public boolean isDone = false;

    public TranslateForMe(String[] TranslateArray) {
        WordsToTranslate = TranslateArray;
    }


    public void TranslateWords() {
        Log.e("TAG","Translation Has Started");
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {

                //Translate translate = TranslateOptions.getDefaultInstance().getService();
               //  com.memetix.mst.translate.Translate.setClientId("AnthonyEssaye");
               // com.memetix.mst.translate.Translate.setClientSecret("a5f63baeeb9947ee9fbed3fda6c70770");



                try {


                    for (int i = 0; i < WordsToTranslate.length; i++) {
                        // translation = translate.translate(WordsToTranslate[i], Translate.TranslateOption.targetLanguage("ar"));

                            String encoded_query = URLEncoder.encode(WordsToTranslate[i], "UTF-8");
                            String continuation = "?to=" + target + "&text=" + encoded_query;
                            URL url = new URL(host + path + continuation);

                            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                            connection.setRequestMethod("GET");
                            connection.setRequestProperty("Ocp-Apim-Subscription-Key", subscriptionKey);
                            connection.setDoOutput(true);

                            StringBuilder response = new StringBuilder();
                            BufferedReader in = new BufferedReader(
                                    new InputStreamReader(connection.getInputStream()));
                            String line;
                            while ((line = in.readLine()) != null) {
                                response.append(line);
                            }
                            WordsToTranslate[i] = response.toString();
                            in.close();




                    }
                }

                catch (Exception e) {}
                isDone = true;
                Log.e("TAG","Translation Has Ended");

                return null;
            }


        }.execute();

    }

    public String[] getWordsToTranslate(){
        return WordsToTranslate;
    }





}



