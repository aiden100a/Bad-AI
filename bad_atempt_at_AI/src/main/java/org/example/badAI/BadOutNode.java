package org.example.badAI;

import java.util.ArrayList;

public class BadOutNode extends BadNode {
    private double num;

    public BadOutNode(long pointNumber) {
        super(null, null,pointNumber);
    }

    @Override
    public void equate(double input) {
        num += input;
    }

    public double getNum() {
        return num;
    }

    @Override
    public BadOutNode copy(ArrayList<BadNode> madeNodes) {
        return new BadOutNode(getPointNumber());
    }
}
