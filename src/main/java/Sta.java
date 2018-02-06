package main.java;

import javafx.scene.paint.Color;

public class Sta {
//                              [0-7] - index of sequece array
//            []  []  []  7     for example, changes in cell[2][0]
//            []  []  []  6     entails changes in sequence array with index 0, 3, 7
//            []  []  []  5
//          0  1   2   3  4
    final static int[][][] cellSeq = new int[][][]{
            {{1, 4, 7}, {1, 6}, {0, 1, 5}},
            {{2, 7}, {0, 2, 4, 6}, {2, 5}},
            {{0, 3, 7}, {3, 6}, {3, 4, 5}}};
//  size of gamecell
    static int smallSize;
//  distance between cells
    static int smallDx;
//  size of small game field. depends of smallSize & smallDx
    static int bigSize;
//  distance between small game fields
    private static int bigDx;
//  current player sign
    static int XO;
//  flag of end of game
    static boolean finish;

//  color of background
    static final Color clrBack = Color.BROWN;
//  color of non active cells
    static final Color clrCell = Color.GOLD;
//  color of available cells
    static final Color clrAvailable = Color.GOLDENROD;
//  color of winner
    static final Color clrWin = Color.RED;
//  color of was maiden XO
    public static final Color clrTxtSimple = Color.BLACK;
//  color for possibles steps
    public static final Color clrTxtAvailable = Color.GREEN;

    static final Color clrTxtX = Color.RED;
    static final Color clrTxtXw = Color.DARKRED;
    static final Color clrTxt0 = Color.BLUE;
    static final Color clrTxt0w = Color.DARKBLUE;

//  never used but...
    int[] getSeq(int i, int j) {
        return cellSeq[i][j];
    }
//  big game field
    static SmField[][] bigField = new SmField[3][3];

    Sta() {
        smallSize = 50;
        smallDx = 2;
        bigSize = 3 * smallSize + 4 * smallDx;
        bigDx = 4;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                bigField[i][j] = new SmField();
            }
        }
    }
}
