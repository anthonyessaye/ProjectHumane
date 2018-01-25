package com.themodernbit.emerald.StaticClasses;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.themodernbit.emerald.R;
import com.themodernbit.emerald.ScenariosPackage.Scenario;

import java.util.List;

/**
 * Created by antho on 1/26/2018.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder>{

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView courseName;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            courseName = (TextView)itemView.findViewById(R.id.RepeatTextView);
        }
    }


    List<Scenario> theScenario;

    public RVAdapter(List<Scenario> scenarios){
        this.theScenario = scenarios;
    }

    @Override
    public int getItemCount() {
        return theScenario.size();
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_layout, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }


    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.courseName.setText(theScenario.get(i).getScenarioName());

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
