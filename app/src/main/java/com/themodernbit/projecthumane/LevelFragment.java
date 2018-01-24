package com.themodernbit.projecthumane;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.themodernbit.projecthumane.ScenariosPackage.Scenario;
import com.themodernbit.projecthumane.ScenariosPackage.ScenarioDBHandler;
import com.themodernbit.projecthumane.StaticClasses.ScenarioDBStaticData;
import com.themodernbit.projecthumane.StaticClasses.StableArrayAdapter;
import com.themodernbit.projecthumane.TagsActivities.ChosenTranslationActivity;

import java.util.ArrayList;
import java.util.HashMap;


public class LevelFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    public LevelFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static LevelFragment newInstance(String param1, String param2) {
        LevelFragment fragment = new LevelFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View theView = (View) inflater.inflate(R.layout.fragment_level, container, false);

        ScenarioDBHandler dbHandler = new ScenarioDBHandler(theView.getContext(), null, null, 1);
        dbHandler.FillScenarioDB();

        Scenario[] theScenarios = dbHandler.getScenario();

        final ListView listview = (ListView) theView.findViewById(R.id.listviewLevelFragment);

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < 4; i++) {
            list.add(theScenarios[i].getScenarioName());
        }

        final StableArrayAdapter adapter = new StableArrayAdapter(theView.getContext(), R.layout.text_view_layout, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);

                //Intent theIntent = new Intent(t, ChosenTranslationActivity.class);
               // theIntent.putExtra(KEY_TAG_TOTRANSLATE, item.toString());
                //startActivity(theIntent);

            }

        });


        return theView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            mListener = (LevelFragment.OnFragmentInteractionListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
