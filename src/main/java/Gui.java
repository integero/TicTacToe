package main.java;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

//  all things for user GUI
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
//      height of font is being installed as cell size
        gc.setFont(Font.font(Sta.smallSize));
        root.getChildren().add(canvas);
    }

    //  paint small game field. If available - paint all possible cells for step
    private void paintIJ(int bigI, int bigJ, boolean available) {
        int[][] cells = Sta.bigField[bigI][bigJ].getSmallField();

        currColor = (Color) gc.getFill();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
//              cells painting
                gc.fillRect(blockPosition(bigI, i), blockPosition(bigJ, j), Sta.smallSize, Sta.smallSize);
                if (cells[i][j] != 0)
                    paintCell(cells[i][j], bigI, bigJ, i, j, false);
                else if (available && cells[i][j] == 0)
                    paintCell(Sta.XO, bigI, bigJ, i, j, available);
            }
    }

    private void paintCell(int xo, int bigI, int bigJ, int i, int j, boolean av) {
        int st = Sta.bigField[bigI][bigJ].getStateSm();
        String xoTxt = (xo == -1) ? "0" : "X";
        Color txtColor=(xo==-1)?(st==-1)?Sta.clrTxt0w:Sta.clrTxt0:(st==1)?Sta.clrTxtXw:Sta.clrTxtX;
//        if (xo == -1) {
//            xoTxt = "0";
//            txtColor = (st==-1)?Sta.clrTxt0w:Sta.clrTxt0;
//            if (st == -1)
//                txtColor = Sta.clrTxt0w;
//            else
//                txtColor = Sta.clrTxt0;
//        } else {
//            xoTxt = "X";
//            txtColor = (st==1)?Sta.clrTxtXw:Sta.clrTxtX;
//            if (st == 1)
//                txtColor = Sta.clrTxtXw;
//            else
//                txtColor = Sta.clrTxtX;
//        }
        if (av) gc.setGlobalAlpha(0.3);
        gc.setFill(txtColor);
        gc.fillText(xoTxt, leftXO(bigI, i), bottXO(bigJ, j));
        gc.setFill(currColor);
        gc.setGlobalAlpha(1);
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
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                paintIJ(i, j, available);
    }

    //  smallField not available for gamestep
    void paintUntouch(int bigI, int bigJ) {
        gc.setFill(Sta.clrCell);
        paintIJ(bigI, bigJ, false);
    }

    //  smallField available for gamestep
    void paintAvailable(int bigI, int bigJ) {
        gc.setFill(Sta.clrAvlbl);
        paintIJ(bigI, bigJ, true);
    }

    //  WINNER TAKES ALL!!!
    void paintWinner(int winner, int[][] fin) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (fin[i][j] == winner) {
                    gc.setFill(Sta.clrWin);
                    paintIJ(i, j, false);
                    gc.setFill(Sta.clrCell);
                }
//      warning for CLOSE game
        gc.setFill(Color.WHITE);
        gc.fillText("Click at any place ", Sta.smallSize, Sta.bigSize);
        gc.fillText("for close GAME", Sta.smallSize, 2 * Sta.bigSize);
    }
}
