package main.java;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.function.BiFunction;
//  all things for user GUI
public class Gui {
    Stage stage;
    Group root;
    Scene scene;
    Canvas canvas;
    GraphicsContext gc;

    public Gui() {
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
    public void paintIJ(int bigI, int bigJ, boolean available) {
        int[][] cells = Sta.bigField[bigI][bigJ].getSmallField();
//      attempt to use lambda
//        BiFunction<Integer, Integer, Integer> pos = (bIJ, ij) -> bIJ * Sta.bigSize + ij * (Sta.smallSize + Sta.smallDx);

        Color currColor = (Color) gc.getFill();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
//              cells painting
                gc.fillRect(blockPosition(bigI, i), blockPosition(bigJ, j), Sta.smallSize, Sta.smallSize);
//                attempt to use lambda. I don't like it at this point
//                gc.fillRect(pos.apply(bigI, i), pos.apply(bigJ, j), Sta.smallSize, Sta.smallSize);

                if (cells[i][j] != 0) {
//                  painting steps was maiden
                    gc.setFill(Sta.clrTxtSimple);
                    gc.fillText((cells[i][j] == -1) ? "0" : "X", leftXO(bigI, i), bottXO(bigJ, j));
                    gc.setFill(currColor);
                } else if (available) {
//                  painting possible steps
                    gc.setFill(Sta.clrTxtAvailable);
                    gc.fillText((Sta.XO == -1) ? "0" : "X", leftXO(bigI, i), bottXO(bigJ, j));
                    gc.setFill(currColor);
                }
            }
        }

    }
//  smallField left & top position evaluation
    int blockPosition(int bigIJ, int ij) {
        return bigIJ * Sta.bigSize + ij * (Sta.smallSize + Sta.smallDx);
    }
//  XO left position evaluation
    int leftXO(int bigI, int i) {
        return blockPosition(bigI, i) + 4 * Sta.smallDx;
    }
//  XO bottom position evaluation
    int bottXO(int bigJ, int j) {
        return blockPosition(bigJ, j) + Sta.smallSize - 3 * Sta.smallDx;
    }
//  painting all smallFields at the begin of game
    public void paintAll() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                paintIJ(i, j, false);
    }
//  smallField not available for gamestep
    public void paintUntouch(int bigI, int bigJ) {
        gc.setFill(Sta.clrCell);
        paintIJ(bigI, bigJ, false);
    }
//  smallField available for gamestep
    public void paintAvailable(int bigI, int bigJ) {
        gc.setFill(Sta.clrAvailable);
        paintIJ(bigI, bigJ, true);
    }
//  WINNER TAKES ALL!!!
    public void paintWinner(int winner, int[][] fin) {
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
