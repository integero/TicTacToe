package com.apache.my;

import javafx.scene.paint.Color;

class Sta {
//                              [0-7] - index of sum & count arrays
//            []  []  []  7     for example, changes in cell[2][0]
//            []  []  []  6     entails changes in sequence array with index 0, 3, 7
//            []  []  []  5
//          0  1   2   3  4

    final static int[][][] cellSeq = new int[][][]{
            {{1, 4, 7}, {1, 6}, {0, 1, 5}},
            {{2, 7}, {0, 2, 4, 6}, {2, 5}},
            {{0, 3, 7}, {3, 6}, {3, 4, 5}}};

    static int smallSize;       //  size of gamecell
    static int smallDx;         //  distance between cells
    static int bigSize;         //  size of small game field. depends of smallSize & smallDx
    private static int bigDx;   //  distance between small game fields
    static int XO;              //  current player sign
    static boolean finish;      //  flag of end of game

    static final Color clrBack = Color.BROWN;           //  color of background
    static final Color clrCell = Color.GOLD;            //  color of non active cells
    static final Color clrAvlbl = Color.GOLDENROD;      //  color of available cells
    static final Color clrWin = Color.LAVENDER;         //  color of winner

    static final Color clrTxtX = Color.RED;             //  color for possibles steps
    static final Color clrTxtXw = Color.DARKRED;        //  color of was maiden X
    static final Color clrTxt0 = Color.BLUE;            //  color for possibles steps
    static final Color clrTxt0w = Color.DARKBLUE;       //  color of was maiden O

    static  Color[] bigCellClr;
//  never used but...
    int[] getSeq(int i, int j) {
        return cellSeq[i][j];
    }

    static SmField[][] bigField;    //  big game field

    Sta() {
        smallSize = 50;
        smallDx = 2;
        bigSize = 3 * smallSize + 4 * smallDx;
        bigDx = 4;
        bigField = new SmField[3][3];
        bigCellClr = new Color[3];
        bigCellClr[0] = Color.LIGHTBLUE;
        bigCellClr[1] = Color.GOLD;
        bigCellClr[2] = Color.ORANGERED;

        for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) bigField[i][j] = new SmField();
    }
}
