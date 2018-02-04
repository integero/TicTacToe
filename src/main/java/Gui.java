package main.java;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

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
        gc.setFont(Font.font(Sta.smallSize));
        root.getChildren().add(canvas);
    }

    public void paintIJ(int bigI, int bigJ, boolean available) {
        int[][] cells = Sta.bigField[bigI][bigJ].getSmallField();
        int size = Sta.smallSize;
        int sD = Sta.smallDx;
        int leftI = bigI * Sta.bigSize;
        int topJ  = bigJ * Sta.bigSize;
        Color currColor = (Color) gc.getFill();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int lefti = leftI + i * (size + sD);
                int topj = topJ + j * (size + sD);
                gc.fillRect(lefti, topj, size, size);

                if (cells[i][j] != 0) {
                    gc.setFill(Sta.clrTxtSimple);
                    gc.fillText((cells[i][j] == -1) ? "0" : "X", lefti + 4 * sD, topj + size - 3 * sD);
                    gc.setFill(currColor);
                } else if (available) {
                    gc.setFill(Sta.clrTxtAvailable);
                    gc.fillText((Sta.XO == -1) ? "0" : "X", lefti + 4 * sD, topj + size - 3 * sD);
                    gc.setFill(currColor);
                }
            }
        }
    }

    public void paintAll() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                paintIJ(i, j,false);
    }

    public void paintUntouch(int bigI, int bigJ) {
        gc.setFill(Sta.clrCell);
        paintIJ(bigI, bigJ, false);
    }

    public void paintAvailable(int bigI, int bigJ) {
        gc.setFill(Sta.clrAvailable);
        paintIJ(bigI, bigJ, true);
    }

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
        gc.setFill(Color.WHITE);
        gc.fillText("Click at any place ",Sta.smallSize,Sta.bigSize);
        gc.fillText("for close GAME",Sta.smallSize,2*Sta.bigSize);
    }
}
