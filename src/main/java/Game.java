package main.java;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.stage.Stage;
import static java.lang.Math.abs;

public class Game extends Application {

    private int[][] bigCells;       //  game cells
    private int[] sum3big;          //  Contains sum for all direction in bigCells. Let you see Sta.
    private int bigI;
    private int bigJ;
    private boolean startNfull;
    private Gui gui;
    private int winer;

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
        node.setOnMousePressed(event -> {
            if (Sta.finish) gui.stage.close();
//          canvas click position
            int i = (int) event.getX();
            int j = (int) event.getY();
//          smallField chosen
            int iB = i / Sta.bigSize;
            int jB = j / Sta.bigSize;
//          start game or fool smallField focusing

            if (startNfull) {
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
                if (Sta.bigField[iB][jB].isCellFree(is, js))
                    putXO(is,js);
            }
        });
    }

    private void putXO(int is, int js) {
        boolean bigChange;

        bigChange = Sta.bigField[bigI][bigJ].setIJ(is, js, Sta.XO);
        gui.paintUntouch(bigI, bigJ);
        if ( bigChange ) {
            Sta.finish = setBigIJ();
            if (Sta.finish) return;
        }
        Sta.XO = -Sta.XO;
        if (Sta.bigField[is][js].isFull()) {
            startNfull = true;
            gui.paintAll(true);
            gui.paintUntouch(is,js);
            return;
        }
        bigI = is;
        bigJ = js;
        gui.paintAvailable(bigI, bigJ);
    }

    private boolean setBigIJ() {
        boolean tmpBoo = false;
        bigCells[bigI][ bigJ]=Sta.XO;
        int tmp[] = Sta.cellSeq[bigI][bigJ];
        int stateTmp = 0;
        for (int k : tmp) {
            sum3big[k] += Sta.XO;
            if (Sta.XO == 1) {
                if (sum3big[k] > stateTmp) stateTmp = sum3big[k];
            } else if (sum3big[k] < stateTmp) stateTmp = sum3big[k];
        }
        if (abs(stateTmp) == 3) {
            gui.paintWinner(Sta.XO, bigCells);
            tmpBoo = true;
        }
        return tmpBoo;
    }
}
