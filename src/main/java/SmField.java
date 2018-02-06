package main.java;

import static java.lang.Math.abs;

public class SmField {
//    amount of free cells
    private int freeCells;
//  0 - not occupied    -1 - occ by ZERO    1 - occ by CROSS
    private int stateSm;
//  game cells
    private int[][] cellsOfField;
//  sequence for this smallField. Contains sum for all direction in smallField. Let you see java.java.Sta.
    private int[] sequence;

    public SmField() {
        freeCells = 9;
        this.stateSm = 0;
        this.cellsOfField = new int[3][3];
        sequence = new int[8];
    }

    public boolean isCellFree(int i, int j) {
        return cellsOfField[i][j] == 0;
    }

//  CROSS XO. return true if success
    public boolean setOij(int i, int j) {
        return setIJ(i, j, -1);
    }

//  ZERO XO. return true if success
    public boolean setXij(int i, int j) {
        return setIJ(i, j, 1);
    }

//  XO by ZERO or CROSS
    private boolean setIJ(int i, int j, int XO) {
        boolean boTmp = false;
        freeCells--;
        cellsOfField[i][j] += XO;
//  recalculating sums of necessary directions for placing XO in cell (i,j), IF smallField is not occupied
        if (stateSm == 0) {
            int tmp[] = Sta.cellSeq[i][j];
            int stateTmp = 0;
            for (int k : tmp) {
                sequence[k] += XO;
                if (XO == 1) {
                    if (sequence[k] > stateTmp) stateTmp = sequence[k];
                } else if (sequence[k] < stateTmp) stateTmp = sequence[k];
            }
            if (abs(stateTmp) == 3) {
                this.stateSm = XO;
                boTmp = true;
            }
        }
        return boTmp;
    }

//  current state may be useful in future
    public int getStateSm() {
        return stateSm;
    }

//  is all cells occupied may be usefulin future
    public boolean isFull() {
        return freeCells == 0;
    }

//    for debugging only
    public int[] getSequence() {
        return sequence;
    }

    public int[][] getSmallField() {
        return cellsOfField;
    }

}
