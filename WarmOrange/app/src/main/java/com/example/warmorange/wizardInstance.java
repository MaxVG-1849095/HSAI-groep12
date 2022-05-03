package com.example.warmorange;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class wizardInstance {
    private Vector<String> questions = new Vector<>();
    private Map<String, ArrayList<String>> answers = new HashMap<>();
    private ArrayList<Integer> responses = new ArrayList<>();
    private int currentIndex = 0;
    public wizardInstance(){
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
    public int calcResponse(){
        int total = 0;
        for (int i:responses
             ) {
            total += i;
        }
        return total;
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





}
