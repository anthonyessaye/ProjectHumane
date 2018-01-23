package com.themodernbit.projecthumane.StaticClasses;

import android.content.Context;

import com.themodernbit.projecthumane.ScenariosPackage.Scenario;
import com.themodernbit.projecthumane.ScenariosPackage.ScenarioDBHandler;

/**
 * Created by antho on 1/23/2018.
 */

public class ScenarioDBStaticData {

    public static Context baseContext;

    public ScenarioDBStaticData(Context context){
        baseContext = context;
    }

    public void FillScenarioDB(){
        ScenarioDBHandler theScenariosHandler = new ScenarioDBHandler(baseContext, null, null, 1);

        Scenario theScenario = new Scenario(0, "Medical Scenario");
        theScenariosHandler.insertScenario(theScenario);

        theScenario = new Scenario(1,"Job Interview Scenario");
        theScenariosHandler.insertScenario(theScenario);

    }
}
