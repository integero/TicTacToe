import static java.lang.Math.abs;

public class SmField {
    private int smallState;
    private int[][] cellsOfField;
    private int[] sequence;
    private int[][][] cellSeq;

    public SmField() {
        this.smallState = 0;
        this.cellsOfField = new int[3][3];

        sequence = new int[8];
//        sequence = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        cellSeq = new int[][][]{
                {{1, 4, 7}, {1, 6}, {0, 1, 5}},
                {{2, 7}, {0, 2, 4, 6}, {2, 5}},
                {{0, 3, 7}, {3, 6}, {3, 4, 5}}};
    }

    public boolean setXij(int i, int j) {
        return setIJ(i, j, 1);
    }
    public boolean setOij(int i, int j) {
        return setIJ(i, j, -1);
    }
    boolean setIJ(int i, int j, int XO) {
        if (cellsOfField[i][j] != 0) return false;
        cellsOfField[i][j]+=XO;
        int tmp[]=cellSeq[i][j];
        int state = 0;
        for (int k : tmp) {
            sequence[k]+=XO;
            if (XO==1) if (sequence[k]>state) state = sequence[k];
                else if (sequence[k]<state) state = sequence[k];
        }
        if (abs(state )==3) smallState = XO;
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
