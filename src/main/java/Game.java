package main.java;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.stage.Stage;

import static java.lang.Math.abs;

public class Game extends Application {
    //  game cells
    private int[][] bigCells;
    //  sequence for this smallField. Contains sum for all direction in smallField. Let you see java.java.Sta.
    private int[] sequence;
    private int bigI;
    private int bigJ;
    boolean startAfull;
    Gui gui;
    int winer;

    public Game() {
        new Sta();
        this.bigCells = new int[3][3];
        sequence = new int[8];
        startAfull = true;
        Sta.XO = 1;
        winer = 0;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        gui = new Gui();
//      All smallField first time painting
        gui.paintAll();

        gui.stage.setScene(gui.scene);
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
            if (startAfull) {
                startAfull = false;
                bigI = iB;
                bigJ = jB;

                gui.paintAvailable(bigI, bigJ);
                return;
            }
            if (iB == bigI && jB == bigJ) {

                int is = (i - iB * Sta.bigSize) / Sta.smallSize;
                int js = (j - jB * Sta.bigSize) / Sta.smallSize;
                if (Sta.bigField[iB][jB].isCellFree(is, js)) putXO(is,js);
            }
        });
    }

    private void putXO(int is, int js) {
        boolean bigChange;
        if (Sta.XO ==1)
            bigChange=Sta.bigField[bigI][bigJ].setXij(is, js);
        else
            bigChange=Sta.bigField[bigI][bigJ].setOij(is, js);

        gui.paintUntouch(bigI, bigJ);
        if ( bigChange ) {
            Sta.finish = setBigIJ();
            if (Sta.finish) return;
        }
        Sta.XO = -Sta.XO;
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
            sequence[k] += Sta.XO;
            if (Sta.XO == 1) {
                if (sequence[k] > stateTmp) stateTmp = sequence[k];
            } else if (sequence[k] < stateTmp) stateTmp = sequence[k];
        }
        if (abs(stateTmp) == 3) {
            gui.paintWinner(Sta.XO, bigCells);
            tmpBoo = true;
        }
        return tmpBoo;
    }
}
