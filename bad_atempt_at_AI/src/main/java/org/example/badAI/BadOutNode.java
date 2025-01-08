package org.example.badAI;

public class BadOutNode extends BadNode {
    private double num;

    public BadOutNode() {
        super(null, null);
    }

    @Override
    public void equate(double input) {
        num+=input;
    }
    public double getNum(){
        return num;
    }
}
