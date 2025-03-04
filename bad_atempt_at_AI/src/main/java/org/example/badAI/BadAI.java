package org.example.badAI;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.lang.Math;
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
        jumpStart();

    }

    public BadAI(ArrayList<BadOutNode> outNodes, ArrayList<BadNode> nodes, ArrayList<BadNode>[] inNodes, long point) {

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
        for (BadOutNode i : outNodes) {
            BadOutNode newOutNode = i.copy(null);
            newOutNodes.add(newOutNode);
            newNodes.add(newOutNode);
        }
        //copy all nodes

        for (int i = 0; i < inputNodes.length; i++) {
            for (int j = 0; j < inputNodes[i].size(); j++) {
                BadNode foundNode = inputNodes[i].get(j).getInArrayByPointerNumberAndNotPointer(newNodes);
                if (foundNode != null) {
                    BadNode c = inputNodes[i].get(j).copy(newNodes);
                    newInputNodes[i].add(j, c);
                    newNodes.add(c);
                } else {
                    newInputNodes[i].set(j, foundNode);
                }
            }
        }
        return new BadAI(newOutNodes, newNodes, newInputNodes, currentPoint);

    }

    private void jumpStart() {
        for (ArrayList i : inputNodes) {
            currentPoint++;
            BadNode newNode = new BadNode(outNodes.get((int) (Math.random() * outNodes.size())), outNodes.get((int) (Math.random() * outNodes.size())), currentPoint);
            nodes.add(newNode);
            i.add(newNode);

        }
    }


    /**
     * this modifys the AI By:
     * 1.adding nodes
     * 2.changing the function of nodes.
     *
       this function is bad do not look at it ever, if you do you will surely suffer many headaches

     **/
    public void scramble() {
        double rand = Math.random() * 100;


        if (rand < 33) {
            //scramble node
            if (Math.random()>0.5) {
                nodes.get((int) Math.round(Math.random() * nodes.size())).scrambleType();
            }else {
                nodes.get((int) Math.round(Math.random() * nodes.size())).scrambleNum();
            }
        } else if (33 < rand && rand < 66) {
            //new input or other node to output
            if (Math.random() > 0.5) {

                currentPoint++;
                BadNode newNode = new BadNode(outNodes.get((int) (Math.random() * outNodes.size())), outNodes.get((int) (Math.random() * outNodes.size())), currentPoint);
                inputNodes[(int) (inputNodes.length * Math.random())].add(newNode);
                nodes.add(newNode);
                //dear god.
            } else {
                currentPoint++;
                BadNode targetNode = nodes.get((int) (Math.random() * nodes.size()));
                BadNode newNode = new BadNode(targetNode.getOut()[(int) (Math.random() * 2)], outNodes.get((int) (Math.random() * outNodes.size())), currentPoint);
                targetNode.rewire(newNode, targetNode.getOut()[2]);
                nodes.add(newNode);
            }

        } else {
            currentPoint++;
            int targetArray = (int) (inputNodes.length * Math.random());
            BadNode targetNode = inputNodes[targetArray].get((int) (inputNodes[targetArray].size() * Math.random()));

            BadNode c = new BadNode(targetNode,outNodes.get((int) (Math.random() * outNodes.size())),currentPoint);

            inputNodes[(int)(Math.random()*inputNodes.length)].add(c);
            nodes.add(c);

        }
    }

    public double[] simulate(double[] input, boolean resetOnStart) throws ArrayIndexOutOfBoundsException {
        if (resetOnStart) {
            for (BadOutNode i : outNodes) {
                i.reset();
            }
        }
        if (input.length != inputNodes.length) {
            throw new ArrayIndexOutOfBoundsException("provided array is too long or too short");
        }
        for (int i = 0; i <= input.length; i++) {
            for (int x = 0; x <= inputNodes[i].size(); x++) {
                inputNodes[i].get(x).equate(input[i]);
            }
        }
        double[] returnArray = new double[outNodes.size()];
        for (int i = 0; i < outNodes.size(); i++) {
            returnArray[i] = outNodes.get(i).getNum();
        }
        return returnArray;
    }

    public double[] getLastOutput() {
        double[] returnArray = new double[outNodes.size()];
        for (int i = 0; i < outNodes.size(); i++) {
            returnArray[i] = outNodes.get(i).getNum();
        }
        return returnArray;
    }

    public void resetOut() {
        for (BadOutNode i : outNodes) {
            i.reset();
        }
    }
}
