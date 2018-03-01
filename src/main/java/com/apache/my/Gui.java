package com.apache.my;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

//  all things for GUI
class Gui {
    Stage stage;
    private Group root;
    Scene scene;
    Canvas canvas;
    private GraphicsContext gc;
    private Color currColor;

    Gui() {
        stage = new Stage();
        root = new Group();
        scene = new Scene(root, 3 * Sta.bigSize, 3 * Sta.bigSize, Sta.clrBack);
        canvas = new Canvas(3 * Sta.bigSize, 3 * Sta.bigSize);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Sta.clrCell);
        gc.setFont(Font.font(Sta.smallSize));   //  height of font is being installed as cell size
        root.getChildren().add(canvas);
    }

    //  paint small game field. If it is available - paint all possible cells for step
    private void paintIJ(int bigI, int bigJ, boolean available) {
//      take small field in (bigI,bigJ) position of big field
        int[][] cells = Sta.bigField[bigI][bigJ].getSmallField();
        int whoIsThere = Sta.bigField[bigI][bigJ].getStateSm();
        gc.setFill(Sta.bigCellClr[whoIsThere+1]);       //  set color of the big cell being drawn

        currColor = (Color) gc.getFill();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
//              cells painting
                gc.fillRect(blockPosition(bigI, i), blockPosition(bigJ, j), Sta.smallSize, Sta.smallSize);
                if (cells[i][j] != 0)
                    paintXO(cells[i][j], bigI, bigJ, i, j, false);
                else if (available)
                    paintXO(Sta.XO, bigI, bigJ, i, j, true);
            }
    }
//  paint symbols 0 & X with convenient colors
    private void paintXO(int xo, int bigI, int bigJ, int i, int j, boolean available) {
        if (available) gc.setGlobalAlpha(0.3);          //   set transparency
        int whoIsOwner = Sta.bigField[bigI][bigJ].getStateSm();
//      set color for 0 or X with respect of what is xo & who is owner of small field
        gc.setFill((xo==-1)?(whoIsOwner==-1)?Sta.clrTxt0w:Sta.clrTxt0:(whoIsOwner==1)?Sta.clrTxtXw:Sta.clrTxtX);
//      paint symbol 0 or X
        gc.fillText((xo == -1) ? "0" : "X", leftXO(bigI, i), bottXO(bigJ, j));
        gc.setFill(currColor);                          //  restore small field color
        if (available) gc.setGlobalAlpha(1);            //  restore transparency
    }

//  smallField left & top position evaluation
    private int blockPosition(int bigIJ, int ij) {
        return bigIJ * Sta.bigSize + ij * (Sta.smallSize + Sta.smallDx);
    }
//  XO left position evaluation
    private int leftXO(int bigI, int i) {
        return blockPosition(bigI, i) + 4 * Sta.smallDx;
    }

//  XO bottom position evaluation
    private int bottXO(int bigJ, int j) {
        return blockPosition(bigJ, j) + Sta.smallSize - 3 * Sta.smallDx;
    }

//  painting all smallFields at the begin of game
    void paintAll(boolean available) {
        for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) paintIJ(i, j, available);
    }

//  smallField not available for game step
    void paintUntouch(int bigI, int bigJ) {
        gc.setFill(Sta.clrCell);
        paintIJ(bigI, bigJ, false);
    }

//  smallField available for game step
    void paintAvailable(int bigI, int bigJ) {
        gc.setFill(Sta.clrAvlbl);
        paintIJ(bigI, bigJ, true);
    }

//  WINNER TAKES ALL!!!
    void paintWinner(int winner, int[][] fin) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (fin[i][j] == winner) {
                    gc.setFill(Sta.clrWin);         //  winner color
                    paintIJ(i, j, false);
                    gc.setFill(Sta.clrCell);        //  non active cell color
                }
//      warning for CLOSE game
        gc.setFill(Color.WHITE);
        gc.fillText("Click at any place ", Sta.smallSize, Sta.bigSize);
        gc.fillText("for close GAME", Sta.smallSize, 2 * Sta.bigSize);
    }
//  paint for dead heat (draw)
    void paintDeadHeat() {
        gc.setFill(Color.WHITE);
        gc.fillText("!!!DRAW!!! ", Sta.smallSize,Sta.bigSize);
        gc.fillText("look at ", Sta.smallSize, 2*Sta.bigSize);
        gc.fillText("And click anywhere ", Sta.smallSize, 3*Sta.bigSize);
    }
}
