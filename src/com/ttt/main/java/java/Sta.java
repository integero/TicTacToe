package com.ttt.java;

public class Sta {
    //                        0     [0-7] - index of sequece array
//            []  []  []  1     for example, changes in cell[2][0]
//            []  []  []  2     entails changes in sequence array with index 0, 3, 7
//            []  []  []  3
//             7   6   5  4
    public final static int[][][] cellSeq = new int[][][]{
            {{1, 4, 7}, {1, 6}, {0, 1, 5}},
            {{2, 7}, {0, 2, 4, 6}, {2, 5}},
            {{0, 3, 7}, {3, 6}, {3, 4, 5}}};

    public static int smallSize;
    public static int smallDx;
    public static int bigSize;
    public static int bigDx;

    int[] getSeq(int i, int j) {
        return cellSeq[i][j];
    }

    public static SmField[][] bigField = new SmField[3][3];

    public Sta() {
        smallSize = 50;
        smallDx = 2;
        bigSize = 3 * smallSize + 4 * smallDx;
        bigDx = 4;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                bigField[i][j] = new SmField();
            }
        }
//        for TEST
/*
        bigField[1][2].setOij(0, 0);
        bigField[1][2].setOij(1, 0);
        bigField[1][2].setOij(0, 1);
        bigField[1][2].setXij(0, 2);
*/
    }
}
