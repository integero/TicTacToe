package com.apache.my;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.util.Arrays;
import static java.lang.Math.abs;

public class Game extends Application {

    private int[][] bigCells;       //  game cells
    private int[] sum3big;          //  Contains sum for all direction in bigCells. Let you see Sta.
    private int bigI;
    private int bigJ;
    private boolean startNfull;
    private Gui gui;
    private int winer;
    private boolean draw;

    public Game() {
        new Sta();
        this.bigCells = new int[3][3];
        sum3big = new int[8];
        startNfull = true;
        Sta.XO = 1;
        winer = 0;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        gui = new Gui();
//      All smallField first time painting
        gui.stage.setScene(gui.scene);
        gui.paintAll(true);
        gameListener(gui.canvas);
        gui.stage.show();
    }

    private void gameListener(final Node node) {
        node.setOnMousePressed((MouseEvent event) -> {
            if (Sta.finish || draw) gui.stage.close();
            int i = (int) event.getX();
            int j = (int) event.getY();
            int iB = i / Sta.bigSize;       //  index of block
            int jB = j / Sta.bigSize;       //  where click was maden

            if (startNfull) {     //  for start game & for full target block
                if (Sta.bigField[iB][jB].isFull()) return;
                startNfull = false;
                bigI = iB;
                bigJ = jB;
                gui.paintAll(false);
                gui.paintAvailable(bigI, bigJ);
                return;
            }

            if (iB == bigI && jB == bigJ) {
                int is = (i - iB * Sta.bigSize) / Sta.smallSize;
                int js = (j - jB * Sta.bigSize) / Sta.smallSize;
//           put XO, if click was maiden at convenience place
                if (Sta.bigField[iB][jB].isCellFree(is, js)) putXO(is,js);
            }
            draw = isDraw();
            if (draw) gui.paintDraw();

        });
    }

    private void putXO(int is, int js) {
        boolean bigChange;

        bigChange = Sta.bigField[bigI][bigJ].setIJ(is, js, Sta.XO);
        gui.paintUntouch(bigI, bigJ);

        if ( bigChange ) {              //  het trick was maiden
            Sta.finish = setBigIJ();    //  big field investigation
            if (Sta.finish) return;     //  BIG HET TRICK   WIN!!!
        }
        Sta.XO = -Sta.XO;
        if (Sta.bigField[is][js].isFull()) {    // are all cells occupied?
            startNfull = true;
            gui.paintAll(true);         // repaint full field with possible XO position
            gui.paintUntouch(is,js);             // exclude full target block
            return;
        }
        bigI = is;      //  new
        bigJ = js;      //  target block
        gui.paintAvailable(bigI, bigJ);
    }

    private boolean setBigIJ() {
        int tmp[] = Sta.cellSeq[bigI][bigJ];
        bigCells[bigI][ bigJ]=Sta.XO;
        boolean flag = false;
        for (int k : tmp) if (abs(sum3big[k] += Sta.XO) == 3) flag = true;
//      WIN painting for BIG HET TRICK
        if (flag) gui.paintWinner(Sta.XO, bigCells);
        return flag;
    }
//  calculation of dead heat possibility
    private boolean isDraw() {
        int[] sum0 = Arrays.copyOf( sum3big,sum3big.length);
        int[] sumX = Arrays.copyOf( sum3big,sum3big.length);
        boolean flag = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (bigCells[i][j]!=0) continue;
                int[] tmp = Sta.cellSeq[i][j];
                if (Sta.bigField[i][j].isCan0())
                    for (int k: tmp) if (++sumX[k] == 3) flag = false;
                if (Sta.bigField[i][j].isCanX())
                    for (int k: tmp) if (--sum0[k] == -3) flag = false;
                if (!flag) break;
            }
        }
        return flag;
    }
}
