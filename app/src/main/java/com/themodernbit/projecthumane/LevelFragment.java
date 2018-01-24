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
import android.widget.ListView;
import android.widget.Toast;

import com.themodernbit.projecthumane.ScenariosPackage.Scenario;
import com.themodernbit.projecthumane.ScenariosPackage.ScenarioDBHandler;
import com.themodernbit.projecthumane.ScenariosPackage.Statements;
import com.themodernbit.projecthumane.StaticClasses.StableArrayAdapter;

import java.util.ArrayList;


public class LevelFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static String KEY_TABNUMBER = "KeyNumber";
    private static String KEY_TRANSFERFIRSTDATA = "FirstTransfer";
    private static String KEY_TRANSFERSECONDDATA = "SecondTransfer";
    private static Statements[] firstPageStatements;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int TabNumber = 0 ;

    public int listCount  = 0;


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
            TabNumber = getArguments().getInt(KEY_TABNUMBER);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View theView = (View) inflater.inflate(R.layout.fragment_level, container, false);




        // IF STATEMENTS HERE TELL ME WHICH DATA TO LOAD FOR EACH TAB
        if(TabNumber == 1) {
            listCount++;
            final ScenarioDBHandler dbHandler = new ScenarioDBHandler(theView.getContext(), null, null, 1);
            dbHandler.FillDB();

            final Scenario[] theScenarios = dbHandler.getScenario();


            final ListView listview = (ListView) theView.findViewById(R.id.listviewLevelFragment);

            final ArrayList<String> list = new ArrayList<String>();
            for (int i = 0; i < 4; i++) {
                list.add(theScenarios[i].getScenarioName() + " - " + theScenarios[i].getScenarioArabicName());
            }



            final StableArrayAdapter adapter = new StableArrayAdapter(theView.getContext(), R.layout.text_view_layout, list);
            listview.setAdapter(adapter);


            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {



                // THIS SHOULD BE CLEANED
                @Override
                public void onItemClick(AdapterView<?> parent, final View view,
                                        int position, long id) {
                    final String item = (String) parent.getItemAtPosition(position);


                    if(listCount == 2) { // HERE WE ARE ON SECOND LIST AND WANT TO CREATE ACTIVITY
                        final Statements[] theStatements = dbHandler.getRelatedAnswers(position);

                        Intent theIntent = new Intent(getContext(), ScenarioExplanationActivity.class);
                        theIntent.putExtra(KEY_TRANSFERFIRSTDATA,firstPageStatements[0].getStatement());
                        //theIntent.putExtra(KEY_TRANSFERSECONDDATA, theStatements);
                        startActivity(theIntent);
                    }

                    if(listCount == 1) {
                        try {
                            final Statements[] theStatements = dbHandler.getRelatedStatements(position);
                            firstPageStatements = new Statements[theStatements.length];
                            firstPageStatements = theStatements;
                            if (theStatements.length != 0) {  //IF THERE ARE STATEMENTS
                                listCount++; // ADD COUNTER TO KNOWN WE ARE ON THE SECOND LIST
                                list.clear(); // CLEARING FIRST LIST
                                for (int i = 0; i < theStatements.length; i++) { //ADD NEW ITEMS
                                    if(theStatements[i].getqID_FK() == -1)
                                    list.add(theStatements[i].getStatement() + " \n " + theStatements[i].getArabicStatement());
                                }

                                final StableArrayAdapter adapter = new StableArrayAdapter(theView.getContext(), R.layout.text_view_layout, list);
                                listview.setAdapter(adapter);
                            } else {
                                Toast.makeText(getContext(), "This category is empty", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                        }

                    }



                }

            });
        }

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
