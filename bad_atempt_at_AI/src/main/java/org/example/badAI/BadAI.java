package org.example.badAI;

import java.util.ArrayList;

public class BadAI {
    private long currentPoint = 0;
    private ArrayList<BadOutNode> outNodes;
    private ArrayList<BadNode> nodes;
    private ArrayList<BadNode>[] inputNodes;

    public BadAI(int numInputs, int numOutputs) {

        nodes = new ArrayList<BadNode>();
        inputNodes = new ArrayList[numInputs];
        for (ArrayList i : inputNodes) {
            i = new ArrayList<BadNode>();
        }
        outNodes = new ArrayList<BadOutNode>();
        for (int i = 0; i < numOutputs; i++) {
            BadOutNode newNode = new BadOutNode(currentPoint);
            outNodes.set(i, newNode);
            nodes.add(newNode);
            currentPoint++;
        }



    }

    public BadAI(ArrayList<BadOutNode> outNodes, ArrayList<BadNode> nodes, ArrayList<BadNode>[] inNodes,long point) {

        this.outNodes = outNodes;
        inputNodes = inNodes;
        this.nodes = nodes;
        currentPoint = point;
    }

    public BadAI copy() {
        ArrayList<BadOutNode> newOutNodes = new ArrayList<>();
        ArrayList<BadNode> newNodes = new ArrayList<>();
        ArrayList<BadNode>[] newInputNodes = new ArrayList[inputNodes.length];
        //make: newInputNodes
        for (ArrayList i : newInputNodes) {
            i = new ArrayList<BadNode>();
        }

        //copy out nodes
        for (BadOutNode i:outNodes){
            BadOutNode newOutNode = i.copy(null);
            newOutNodes.add(newOutNode);
            newNodes.add(newOutNode);
        }
        //copy all nodes
        //TODO: SOON AIDEN PLEASE!!!
        for (int i = 0; i < inputNodes.length; i++) {
            for (int j = 0; j < inputNodes[i].size(); j++) {
                if (inputNodes[i].get(j).isInArrayByPointerNumberAndNotPointer(newNodes)){
                    BadNode c = inputNodes[i].get(j).copy(newNodes);
                    newInputNodes[i].add(j,c);
                    newNodes.add(c);
                }
            }
        }
        return new BadAI(newOutNodes,newNodes,newInputNodes,currentPoint);

    }

    public double[] simulate(double[] input) throws ArrayIndexOutOfBoundsException {
        if (input.length != inputNodes.length) {
            throw new ArrayIndexOutOfBoundsException("provided array is too long or too short");
        }
        for (int i = 0; i <= input.length; i++) {
            for (int x = 0; x <= inputNodes[i].size(); x++) {
                inputNodes[i].get(x).equate(input[i]);
            }
        }
        double[] returnArray = new double[outNodes.size()];
        for (int i = 0; i < outNodes.length; i++) {
            returnArray[i] = outNodes.get(i).getNum();
        }
        return returnArray;
    }
}
