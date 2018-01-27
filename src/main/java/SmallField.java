public class SmallField {
    private int smallState;
    private int[] sequence;
//    private int[] sequence={0, 0, 0, 0, 0, 0, 0, 0};
    private int[][][] cellSeq;
    private Cell[][] smallField;

    public SmallField() {
        this.smallState = 0;
//        sequence = new Integer[8];
        sequence = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        cellSeq= new int[][][]{
                {{1, 4, 7}, {1, 6}, {0, 1, 5}},
                {{2, 7}, {0, 2, 4, 6}, {2, 5}},
                {{0, 3, 7}, {3, 6}, {3, 4, 5}}};

        smallField = new Cell[3][3];
//        smallField[0][0] = new Cell(new Integer[]{sequence[1], sequence[4], sequence[7]});
//        smallField[0][0] = new Cell(new int[]{sequence[1], sequence[4], sequence[7]});
//        smallField[0][1] = new Cell(new int[]{sequence[1], sequence[0]});
//        smallField[0][2] = new Cell(new int[]{sequence[0], sequence[1], sequence[5]});
//        smallField[1][0] = new Cell(new int[]{sequence[2], sequence[7] });
//        smallField[1][1] = new Cell(new Integer[]{sequence[0], sequence[2], sequence[4], sequence[6]});
//        smallField[1][1] = new Cell(new int[]{sequence[0], sequence[2], sequence[4], sequence[6]});
//        smallField[1][2] = new Cell(new int[]{sequence[2], sequence[5]});
//        smallField[2][0] = new Cell(new int[]{sequence[0], sequence[3], sequence[7]});
//        smallField[2][1] = new Cell(new int[]{sequence[3], sequence[6]});
//        smallField[2][2] = new Cell(new Integer[]{sequence[3], sequence[4], sequence[5]});
//        smallField[2][2] = new Cell(new int[]{sequence[3], sequence[4], sequence[5]});
    }

    public int getSmallState() {
        return smallState;
    }

    public int[] getSequence() {
        return sequence;
    }

    public Cell[][] getSmallField() {
        return smallField;
    }
}
