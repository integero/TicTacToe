package main.java;

import com.sun.javafx.font.LogicalFont;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.function.Consumer;
import java.util.function.Supplier;

//  all things for user GUI
class Gui {
    Stage stage;
    Group root;
    Scene scene;
    Canvas canvas;
    GraphicsContext gc;
    Color currColor;

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
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
//              cells painting
                gc.fillRect(blockPosition(bigI, i), blockPosition(bigJ, j), Sta.smallSize, Sta.smallSize);
//            if (cells[i][j]!=0||cells[i][j]==0)
                if (cells[i][j] != 0)
                    paintCell(cells[i][j], bigI, bigJ, i, j, false);
                else if (available && cells[i][j] == 0)
                    paintCell(Sta.XO, bigI, bigJ, i, j, available);
/*
            if (cells[i][j] != 0) {
//                  painting steps was maiden
                gc.setFill(Sta.clrTxtSimple);
                gc.fillText((cells[i][j] == -1) ? "0" : "X", leftXO(bigI, i), bottXO(bigJ, j));

//                if (cells[i][j]==1) //gc.getFont().nativeFont).fontResource).isBold=true;
//                    ((LogicalFont) ((PrismFont) gc.getFont().nativeFont).fontResource).isBold;
//                LogicalFont.SYSTEM.
                    Font font = gc.getFont(Font.getDefault().getStyle().);
//                font.getStyle().

//                gc.getFont();
                gc.setFill(currColor);
            } else if (available) {
//                  painting possible steps
                gc.setFill(Sta.clrTxtAvailable);
                gc.fillText((Sta.XO == -1) ? "0" : "X", leftXO(bigI, i), bottXO(bigJ, j));
                gc.setFill(currColor);
            }
*/
            }
        }

/*
    Consumer<Integer> paintTxt = (Integer xo) -> {
        int st = Sta.bigField[bigI][bigJ].getStateSm();
        String xoTxt;
        Color txtColor;
        if (xo == -1) {
            xoTxt = "0";
            if (st==-1)
                txtColor=Sta.clrTxt0w;
            else
                txtColor = Sta.clrTxt0;
        }else{
            xoTxt = "X";
            if (st==-1)
                txtColor=Sta.clrTxtXw;
            else
                txtColor = Sta.clrTxtX;
        }
//        gc.setFont(Font.font());
        gc.setFill(txtColor);
        gc.fillText(xoTxt,leftXO(bigI, i), bottXO(bigJ, j));
        gc.setFill(currColor);
    };*/

    }

    void paintCell(int xo, int bigI, int bigJ, int i, int j, boolean av) {
        int st = Sta.bigField[bigI][bigJ].getStateSm();
        String xoTxt;
        Color txtColor;
        if (xo == -1) {
            xoTxt = "0";
            if (st == -1)
                txtColor = Sta.clrTxt0w;
            else
                txtColor = Sta.clrTxt0;
        } else {
            xoTxt = "X";
            if (st == -1)
                txtColor = Sta.clrTxtXw;
            else
                txtColor = Sta.clrTxtX;
        }
        if (av) gc.setGlobalAlpha(0.3);
//        gc.setFont(Font.font());
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
        gc.setFill(Sta.clrAvailable);
        paintIJ(bigI, bigJ, true);
    }

    //  WINNER TAKES ALL!!!
    void paintWinner(int winner, int[][] fin) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (fin[i][j] == winner) {
                    gc.setFill(Sta.clrWin);
                    paintIJ(i, j, false);
                    gc.setFill(Sta.clrCell);
                }
            }
        }
//      warning for CLOSE game
        gc.setFill(Color.WHITE);
        gc.fillText("Click at any place ", Sta.smallSize, Sta.bigSize);
        gc.fillText("for close GAME", Sta.smallSize, 2 * Sta.bigSize);
    }
}
