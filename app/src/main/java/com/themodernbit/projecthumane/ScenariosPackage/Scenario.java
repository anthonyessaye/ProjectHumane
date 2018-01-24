package com.themodernbit.projecthumane.ScenariosPackage;

/**
 * Created by antho on 1/23/2018.
 */

// Class to create a scenario
    // ScenarioCompleteness is initialized to false
    // Must watch out for ID conflicts, this might be implemented in main later
//

public class Scenario {
    private int scenarioID;
    private String scenarioName;
    private String scenarioArabicName;
    private boolean isScenarioComplete;

    public Scenario( int ID, String sName, String arabicName){
        this.scenarioID = ID;
        this.scenarioName = sName;
        this.scenarioArabicName = arabicName;
        this.isScenarioComplete = false;
    }

    public Scenario() {}

    public int getScenarioID() {return this.scenarioID;}
    public String getScenarioName() {return this.scenarioName;}
    public boolean getCompleteness() {return this.isScenarioComplete;}
    public String getScenarioArabicName() {return this.scenarioArabicName;}

    public void setScenarioID(int ID) {this.scenarioID = ID;}
    public void setScenarioName(String sName) {this.scenarioName = sName;}
    public void setScenarioComplete(boolean isComplete) {this.isScenarioComplete = isComplete;}
    public void setScenarioArabicName(String arabicName) {this.scenarioArabicName = arabicName;}


}
