package com.example.warmorange.model;

import com.example.warmorange.model.applicationData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class WizardInstance {
    private Vector<String> questions = new Vector<>();
    private Map<String, ArrayList<String>> answers = new HashMap<>();
    private ArrayList<Integer> responses = new ArrayList<>();
    private int currentIndex = 0;
    private String result = "";
    private String date = "";
    public WizardInstance(){
    }

    /**
     * Vraag toevoegen aan de data
     * @param question vraag om toe te voegen
     */
    public void addQuestion(String question){
        questions.add(question);
        answers.put(question, new ArrayList<String>());
    }

    /**
     * Antwoord toevoegen aan de data
     * @param question Vraag waarbij het antwoord hoort
     * @param answer het antwoord
     */
    public void addAnswersForQuestion(String question, String answer){
        answers.get(question).add(answer);
    }

    /**
     * Vraag aan huidige index
     * @return
     */
    public String getQuestionForIndex(){
        return questions.elementAt(currentIndex);
    }

    /**
     * Antwoorden aan huidige index
     * @return
     */
    public ArrayList<String> getAnswersForIndex(){
        return answers.get(questions.elementAt(currentIndex));
    }

    /**
     * Huidige index incrementeren
     */
    public void incrementIndex(){
        currentIndex++;
    }
    /**
     * Huidige index decrementeren
     */
    public void decrementIndex(){
        currentIndex--;
    }

    /**
     * Manuele setter voor huidige index
     * @param i
     */
    public void setCurrentIndex(int i){
        currentIndex = i;
    }
    public int getCurrentIndex(){
        return currentIndex;
    }

    public boolean nextIndexInBounds(){
        return currentIndex+1<questions.size();
    }
    public void addResponse(int r){
        if(responses.size() > currentIndex){
            responses.set(currentIndex,r);
        }
        else{
            responses.add(currentIndex,r);
        }
        System.out.println(responses);
    }
    public int calcScore(){
        int total = 0;
        for (int i:responses
             ) {
            total += i;
        }
        return total;
    }
    public String calcResponse(){
        currentIndex = 0;
        Calendar c = Calendar.getInstance();
        date = c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR) + " " + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE);
        if(applicationData.getInstance().getProductData().getCurrentProduct().getType() == "Televisie"){
            int score = calcScore();
            if(score <= 3){
                result = "Samsung QLED 50Q64A (2021)";
                applicationData.getInstance().getProductData().setCurrentProduct(result);
                return result;
            }
            else if(score <= 6){
                result = "Philips The One (50PUS8506) - Ambilight (2021)";
                applicationData.getInstance().getProductData().setCurrentProduct(result);
                return result;
            }
            else if(score <= 9){
                result = "LG C1 OLED55C16LA - 55 inch - 4K OLED - 2021";
                applicationData.getInstance().getProductData().setCurrentProduct(result);
                return result;
            }
        }
        return "wizard failed";
    }
    public boolean lastQuestion(){
        return currentIndex==questions.size() - 1;
    }
    public boolean prevIndexInBounds(){
        return 0<= currentIndex-1;
    }
    public boolean lastIndex(){
        return currentIndex == questions.size()-1;
    }
    public String getResult(){
        return result;
    }
    public String getDate(){
        return date;
    }
    public ArrayList<Integer> getResponses(){
        return responses;
    }





}
