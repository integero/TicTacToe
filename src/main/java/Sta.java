package main.java;

import javafx.scene.paint.Color;

public class Sta {
//                              [0-7] - index of sequece array
//            []  []  []  7     for example, changes in cell[2][0]
//            []  []  []  6     entails changes in sequence array with index 0, 3, 7
//            []  []  []  5
//          0  1   2   3  4
    public final static int[][][] cellSeq = new int[][][]{
            {{1, 4, 7}, {1, 6}, {0, 1, 5}},
            {{2, 7}, {0, 2, 4, 6}, {2, 5}},
            {{0, 3, 7}, {3, 6}, {3, 4, 5}}};
//  size of gamecell
    public static int smallSize;
//  distance between cells
    public static int smallDx;
//  size of small game field. depends of smallSize & smallDx
    public static int bigSize;
//  distance between small game fields
    public static int bigDx;
//  current player sign
    public static int XO;
//  flag of end of game
    public static boolean finish;

//  color of background
    public static final Color clrBack = Color.BROWN;
//  color of non active cells
    public static final Color clrCell = Color.GOLD;
//  color of available cells
    public static final Color clrAvailable = Color.GOLDENROD;
//  color of winner
    public static final Color clrWin = Color.RED;
//  color of was maiden XO
    public static final Color clrTxtSimple = Color.BLACK;
//  color for possibles steps
    public static final Color clrTxtAvailable = Color.GREEN;
//  neve used but...
    int[] getSeq(int i, int j) {
        return cellSeq[i][j];
    }
//  big game field
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
    }
}
