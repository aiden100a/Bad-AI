package org.example.badAI;

import java.util.ArrayList;

public class BadAI {

    private BadOutNode[] outNodes;
    private BadNode[] nodes;
    private ArrayList<BadNode>[] inputNodes;

    public BadAI(int numInputs,int numOutputs){

        outNodes = new BadOutNode[numOutputs];
        inputNodes = new ArrayList[numInputs];


    }
    public double[] simulate(double[] input) throws ArrayIndexOutOfBoundsException{
        if (input.length != inputNodes.length){
            throw new ArrayIndexOutOfBoundsException("provided array is too long or too short");
        }
        for (int i = 0; i<=input.length;i++){
            for (int x = 0; x<=inputNodes[i].size();x++){
                inputNodes[i].get(x).equate(input[i]);
            }
        }
        double[] returnArray = new double[outNodes.length];
        for (int i = 0; i<outNodes.length;i++){
            returnArray[i] = outNodes[i].getNum();
        }
        return returnArray;
    }
}
