package org.example.badAI.SimpleAI;

import org.example.badAI.BadAI;

import java.util.ArrayList;

public class SimpleAI {
    private ArrayList<BadAI> ais;
    private BadAI previousWinner;
    private int numIn;
    private int numOut;
    private ArrayList<int[]> testAnswers;
    private ArrayList<int[]> testQuestions;

    public SimpleAI(int numberInputs,int numberOutputs){
        numIn = numberInputs;
        numOut = numberOutputs;
        testAnswers = new ArrayList<>();
        testQuestions = new ArrayList<>();
        previousWinner = new BadAI(numberInputs,numberOutputs);
    }
    public void addTest(int[] question,int[] answer){
        if (question.length == numIn && answer.length == numOut) {
            testQuestions.add(question);
            testAnswers.add(answer);
        }else  {
            throw new RuntimeException("provided array is length is too long or short");
        }
    }



}
