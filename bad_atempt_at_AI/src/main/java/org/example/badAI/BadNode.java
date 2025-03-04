package org.example.badAI;

import java.util.ArrayList;

public class BadNode {
    private BadNodeType type;
    private double num;
    private BadNode[] out = new BadNode[2];
    private long pointNumber;


    public BadNode(BadNode out1, BadNode out2, long pointNumber) {
        num = Math.random();
        out[1] = out1;
        out[2] = out2;
        type = getRandomType();
        this.pointNumber = pointNumber;
    }

    public BadNode getInArrayByPointerNumberAndNotPointer(ArrayList<BadNode> check){
        for (BadNode i:check){
            if (i.getPointNumber() == pointNumber){
                return i;
            }
        }
        return null;

    }

    public BadNode copy(ArrayList<BadNode> madeNodes) {
        //holders
        BadNode c1 = null;
        BadNode c2 = null;



        for (BadNode i : madeNodes) {
            if (i.getPointNumber() == out[1].getPointNumber()) {
                // add existing node
                c1 = i;
            }
        }
        if (c1 == null) {
            //recursion D:
            c1 = out[1].copy(madeNodes);
            //make node make itself
        }
        for (BadNode i : madeNodes) {
            if (i.getPointNumber() == out[2].getPointNumber()) {
                // add existing node
                c2 = i;
            }
        }
        if (c2 == null) {
            //recursion D:
            c2 = out[2].copy(madeNodes);
            //make node make itself
        }
        return new BadNode(c1, c2, pointNumber);


    }

    public long getPointNumber() {
        return pointNumber;
    }

    public void scrambleType() {
        type = getRandomType();
    }

    public void scrambleNum(){num += Math.random()-0.5;}

    public BadNode[] getOut() {
        return out;
    }

    public void rewire(BadNode out1, BadNode out2) {
        out[1] = out1;
        out[2] = out2;
    }

    public BadNodeType getRandomType() {
        if (Math.random() > 0.5) {
            double x = Math.random();
            if (x > 0 && x < 0.33) {
                return BadNodeType.EQUAL;
            } else if (0.33 < x && 0.66 > x) {
                return BadNodeType.LESS;
            } else {
                return BadNodeType.MORE;
            }
        } else {
            double x = Math.random();
            if (x < 0.25) {
                return BadNodeType.ADD;
            } else if (0.25 < x && 0.5 > x) {
                return BadNodeType.SUB;
            } else if (0.5 < x && 0.75 > x) {
                return BadNodeType.MULT;
            } else {
                return BadNodeType.DIV;
            }

        }
    }

    public BadNodeType getType() {
        return type;
    }


    public void equate(double input) {
        switch (type) {
            case ADD:
                out[1].equate(input + num);
                break;
            case SUB:
                out[1].equate(input - num);
                break;
            case MULT:
                out[1].equate(input * num);
                break;
            case DIV:
                out[1].equate(input / num);
                break;
            case LESS:
                if (input < num) {
                    out[1].equate(num);
                } else {
                    out[2].equate(num);
                }
                break;
            case MORE:
                if (input > num) {
                    out[1].equate(num);
                } else {
                    out[2].equate(num);
                }
                break;
            case EQUAL:
                if (input == num) {
                    out[1].equate(num);
                } else {
                    out[2].equate(num);
                }
                break;
            default:
                out[1].equate(input);
                break;

        }
    }


}
