package com.themodernbit.emerald;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.api.GoogleAPI;
import com.google.api.GoogleAPIException;
import com.google.api.translate.Language;
import com.memetix.mst.translate.Translate;
import com.themodernbit.emerald.TagsActivities.ChosenTranslationActivity;
import com.themodernbit.emerald.StaticClasses.StableArrayAdapter;
import com.themodernbit.emerald.TagsActivities.TranslateForMe;

import java.util.ArrayList;

public class CapturedWordActivity extends AppCompatActivity {

    private static String KEY_LIST_TAGS = "Tags";
    private String TagsListString = "";
    private String[] TagsListArray;

    private TranslateForMe TranslatorObject;
    private static String KEY_TAG_TOTRANSLATE = "Translate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captured_word);
        Intent PreviousIntent = getIntent();


        TagsListString = PreviousIntent.getStringExtra(KEY_LIST_TAGS);
        TagsListArray = TagsListString.split(" ");
        String[] CopyOfList = TagsListString.split(" ");

        TranslatorObject = new TranslateForMe(TagsListArray);
        TranslatorObject.TranslateWords();



        while(!TranslatorObject.isDone){}

        final ListView listview = (ListView) findViewById(R.id.listview);

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < TagsListArray.length; ++i) {
            list.add(CopyOfList[i] + " " + TagsListArray[i]);
        }


        final StableArrayAdapter adapter = new StableArrayAdapter(this, R.layout.text_view_layout, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);

                Intent theIntent = new Intent(getApplicationContext(), ChosenTranslationActivity.class);
                theIntent.putExtra(KEY_TAG_TOTRANSLATE, item.toString());
                startActivity(theIntent);

            }

        });
    }








}