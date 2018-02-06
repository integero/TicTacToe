package main.java;

import static java.lang.Math.abs;

public class SmField {
//    amount of free cells
    private int freeCells;
//  0 - not occupied    -1 - occ by ZERO    1 - occ by CROSS
    private int stateSm;
//  game cells
    private int[][] cellsSF;
//  sum3sm for this smallField. Contains sum for all direction in smallField. Let you see java.java.Sta.
    private int[] sum3sm;
    private int[] count3sm;
    boolean canX;
    boolean can0;

    public SmField() {
        freeCells = 9;
        this.stateSm = 0;
        this.cellsSF = new int[3][3];
        sum3sm = new int[8];
        count3sm = new int[8];
    }

    public boolean isCellFree(int i, int j) {
        return cellsSF[i][j] == 0;
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
        cellsSF[i][j] = XO;
//        cellsSF[i][j] += XO;
//  recalculating sums of necessary directions for placing XO in cell (i,j), IF smallField is not occupied
        if (stateSm == 0) {
            int tmp[] = Sta.cellSeq[i][j];
            int stateTmp = 0;
            for (int k : tmp) {
                sum3sm[k] += XO;
                count3sm[k]++;
                if (XO == 1) {
                    if (sum3sm[k] > stateTmp) stateTmp = sum3sm[k];
                } else if (sum3sm[k] < stateTmp) stateTmp = sum3sm[k];
            }
            if (abs(stateTmp) == 3) {
                this.stateSm = XO;
                boTmp = true;
            } else {
                for (int k = 0; k < 8; k++) {
                    can0 = (count3sm[k] + sum3sm[k]) == 0;
                    if (can0) break;
                }
                for (int k = 0; k < 8; k++) {
                    canX = (count3sm[k] - sum3sm[k]) == 0;
                    if (canX) break;
                }
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
        return sum3sm;
    }

    public int[][] getSmallField() {
        return cellsSF;
    }

    int getCell(int i, int j) {
        return cellsSF[i][j];
    }

}
