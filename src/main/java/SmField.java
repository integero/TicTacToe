import static java.lang.Math.abs;

public class SmField {
    private int smallState;
    private int[][] cellsOfField;
    private int[] sequence;

    public SmField() {
        this.smallState = 0;
        this.cellsOfField = new int[3][3];
        sequence = new int[8];
    }

    public boolean setXij(int i, int j) {
        return setIJ(i, j, 1);
    }

    public boolean setOij(int i, int j) {
        return setIJ(i, j, -1);
    }

    private boolean setIJ(int i, int j, int XO) {
        if (cellsOfField[i][j] != 0) return false;
        cellsOfField[i][j] += XO;
        int tmp[] = Sta.cellSeq[i][j];
        int state = 0;
        for (int k : tmp) {
            sequence[k] += XO;
            if (XO == 1) {
                if (sequence[k] > state) state = sequence[k];
            } else
                if (sequence[k] < state) state = sequence[k];
        }
        if (abs(state) == 3) smallState = XO;
        return true;
    }


    public int getSmallState() {
        return smallState;
    }

    public int[] getSequence() {
        return sequence;
    }

    public int[][] getSmallField() {
        return cellsOfField;
    }

}
