package com.example.warmorange.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

public class WizardData {
    private HashMap<String, WizardInstance> instances = new HashMap<>();
    private Vector<Wizard> wizards = new Vector<>();
    private String wizardType;
    public WizardData(){
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
        instances.put("Televisie", new WizardInstance());
        //vraag 1 tv
        instances.get("Televisie").addQuestion("Hoe groot moet de televisie zijn?");
        Objects.requireNonNull(instances.get("Televisie")).addAnswersForQuestion("Hoe groot moet de televisie zijn?", "Klein");
        Objects.requireNonNull(instances.get("Televisie")).addAnswersForQuestion("Hoe groot moet de televisie zijn?", "Middelmaat");
        Objects.requireNonNull(instances.get("Televisie")).addAnswersForQuestion("Hoe groot moet de televisie zijn?", "Groot");
        Objects.requireNonNull(instances.get("Televisie")).addAnswersForQuestion("Hoe groot moet de televisie zijn?", "Geen mening");
        //vraag 2 tv
        instances.get("Televisie").addQuestion("Heeft u een voorkeur aan schermtype?");
        Objects.requireNonNull(instances.get("Televisie")).addAnswersForQuestion("Heeft u een voorkeur aan schermtype?", "OLED");
        Objects.requireNonNull(instances.get("Televisie")).addAnswersForQuestion("Heeft u een voorkeur aan schermtype?", "LED");
        Objects.requireNonNull(instances.get("Televisie")).addAnswersForQuestion("Heeft u een voorkeur aan schermtype?", "LCD");
        Objects.requireNonNull(instances.get("Televisie")).addAnswersForQuestion("Heeft u een voorkeur aan schermtype?", "Geen mening");
        //vraag 3 tv
        instances.get("Televisie").addQuestion("Heeft u een voorkeur aan resolutie?");
        Objects.requireNonNull(instances.get("Televisie")).addAnswersForQuestion("Heeft u een voorkeur aan resolutie?", "4K");
        Objects.requireNonNull(instances.get("Televisie")).addAnswersForQuestion("Heeft u een voorkeur aan resolutie?", "HD");
        Objects.requireNonNull(instances.get("Televisie")).addAnswersForQuestion("Heeft u een voorkeur aan resolutie?", "Ultra-HD");
        Objects.requireNonNull(instances.get("Televisie")).addAnswersForQuestion("Heeft u een voorkeur aan resolutie?", "Geen mening");
    }
    public WizardInstance getInstance(String type){
        return instances.get(type);
    }
    public List<Wizard>  getWizardList() {
        return Collections.list(wizards.elements());
    }
    public void addWizardData(WizardInstance wizardInstance){
        Wizard wizard = new Wizard(wizardInstance);
        wizards.add(wizard);
    }
}
