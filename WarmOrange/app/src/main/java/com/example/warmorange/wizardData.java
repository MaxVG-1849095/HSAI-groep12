package com.example.warmorange;

import java.util.HashMap;
import java.util.Objects;

public class wizardData {
    private HashMap<String, wizardInstance> instances = new HashMap<>();
    private String wizardType;
    public wizardData(){
        popMap();
        wizardType = "blabla";
    }

    public String getWizardType() {
        return wizardType;
    }

    public void setWizardType(String wizardType) {
        this.wizardType = wizardType;
    }

    /**
     * Functie die data in de hashmap stopt voor de vragen
     */
    private void popMap(){
        instances.put("Television", new wizardInstance());
        //vraag 1 tv
        instances.get("Television").addQuestion("Hoe groot moet de televisie zijn?");
        Objects.requireNonNull(instances.get("Television")).addAnswersForQuestion("Hoe groot moet de televisie zijn?", "Klein");
        Objects.requireNonNull(instances.get("Television")).addAnswersForQuestion("Hoe groot moet de televisie zijn?", "Middelmaat");
        Objects.requireNonNull(instances.get("Television")).addAnswersForQuestion("Hoe groot moet de televisie zijn?", "Groot");
        Objects.requireNonNull(instances.get("Television")).addAnswersForQuestion("Hoe groot moet de televisie zijn?", "Geen mening");
        //vraag 2 tv
        instances.get("Television").addQuestion("Heeft u een voorkeur aan schermtype?");
        Objects.requireNonNull(instances.get("Television")).addAnswersForQuestion("Heeft u een voorkeur aan schermtype?", "OLED");
        Objects.requireNonNull(instances.get("Television")).addAnswersForQuestion("Heeft u een voorkeur aan schermtype?", "LED");
        Objects.requireNonNull(instances.get("Television")).addAnswersForQuestion("Heeft u een voorkeur aan schermtype?", "LCD");
        Objects.requireNonNull(instances.get("Television")).addAnswersForQuestion("Heeft u een voorkeur aan schermtype?", "Geen mening");
        //vraag 3 tv
        instances.get("Television").addQuestion("Heeft u een voorkeur aan resolutie?");
        Objects.requireNonNull(instances.get("Television")).addAnswersForQuestion("Heeft u een voorkeur aan resolutie?", "4K");
        Objects.requireNonNull(instances.get("Television")).addAnswersForQuestion("Heeft u een voorkeur aan resolutie?", "HD");
        Objects.requireNonNull(instances.get("Television")).addAnswersForQuestion("Heeft u een voorkeur aan resolutie?", "Ultra-HD");
        Objects.requireNonNull(instances.get("Television")).addAnswersForQuestion("Heeft u een voorkeur aan resolutie?", "Geen mening");
    }
    public wizardInstance getInstance(String type){
        return instances.get(type);
    }
}
