package com.ttt.java;

public class BigField {
    private int state;
    private SmField[][] cellsOfField;
    private int[] sequence;
    private int smI;
    private int smJ;
    private boolean firstStep;

    public BigField() {
        firstStep = true;
        this.state = 0;
        this.cellsOfField = new SmField[3][3];
        sequence = new int[8];
    }

    public void setXXij(int smI, int smJ) {
        SmField tmpSm = cellsOfField[smI][smJ];
        if (tmpSm.isFull()) {
//            do something & stop?? LOOK at rules
            return;
        }
    }

    public boolean isXXFull(int smI, int smJ) {
        return cellsOfField[smI][smJ].isFull();
    }
}
