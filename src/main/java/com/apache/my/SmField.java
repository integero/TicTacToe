package com.apache.my;

import static java.lang.Math.abs;

class SmField {
    private int freeCells;      //    amount of free cells
    private int stateSm;        //  0 - not occupied    -1 - occ by ZERO    1 - occ by CROSS
    private boolean canX;       //  X can win this field
    private boolean can0;       //  0 can win this field
    private int[][] cellsSF;    //  game cells
    private int[] sum3sm;       //  Contains sum for all direction in cellsSF. Let you see Sta.
    private int[] count3sm;     //  Contains counts of occupied cells for all directions.
//    for example
//                      [X] [ ] [0]  7
//                      [ ] [X] [ ]  6
//                      [ ] [0] [X]  5
//                    0  1   2   3   4   5   6   7
// sum3sm             0  1   0   0   3   0   1   0    we can make conclusion about het trick from this array
// count3sm           2  1   2   2   3   2   1   2    the same for possibility of het trick

    SmField() {
        freeCells = 9;
        stateSm = 0;
        can0 = true;
        canX = true;
        cellsSF = new int[3][3];
        sum3sm = new int[8];
        count3sm = new int[8];
    }

    boolean isCellFree(int i, int j) {
        return cellsSF[i][j] == 0;
    }

    boolean isCanX() {
        return canX;
    }       //  true if X can win

    boolean isCan0() {
        return can0;
    }       //  true if 0 can win

    boolean setIJ(int i, int j, int XO) {   //  putting XO into cell
        boolean isTopThree = false;
        freeCells--;                        //  decrease amount of free cells in current field
        cellsSF[i][j] = XO;
        if (stateSm == 0) {                 //  calculation the current situation if field is not occupied
            int tmp[] = Sta.cellSeq[i][j];  //  indexes of dependent elements in sum3sm & count3sm
            boolean flag = false;           //  is (true) or not (false) top three occurs
            for (int k : tmp) {
                count3sm[k]++;
                if (abs(sum3sm[k] += XO) == 3) flag = true;
            }
            if (flag) {                     //  someone makes a top three
                this.stateSm = XO;
                isTopThree = true;
            } else {                        //  if not, then calculate the possibility in future
//  can0 & canX may be true if amount of occupied cells = amount of 0 or X (respectively) in any direction
                for (int k = 0; k < 8; k++) if (can0 = (count3sm[k] + sum3sm[k]) == 0) break;
                for (int k = 0; k < 8; k++) if (canX = (count3sm[k] - sum3sm[k]) == 0) break;
            }
        }
        return isTopThree;
    }

    int getStateSm() {
        return stateSm;
    }   //  return -1 or 1 if occupied or 0 if not

    boolean isFull() {
        return freeCells == 0;
    }

    int[][] getSmallField() {
        return cellsSF;
    }

}
