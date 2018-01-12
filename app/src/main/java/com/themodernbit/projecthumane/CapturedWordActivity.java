package com.themodernbit.projecthumane;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.themodernbit.projecthumane.TagsActivities.ChosenTranslationActivity;
import com.themodernbit.projecthumane.TagsActivities.TranslateForMe;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;

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

        while(!TranslatorObject.isDone){

        }


        final ListView listview = (ListView) findViewById(R.id.listview);

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < TagsListArray.length; ++i) {
            list.add(CopyOfList[i] + " " + TranslatorObject.getWordsToTranslate()[i] );
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

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  ArrayList<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }





}
