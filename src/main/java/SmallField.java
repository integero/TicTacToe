public class SmallField {
    private int smallState;
    private Integer[] sequence;
    private Cell[][] smallField;

    public SmallField() {
        this.smallState = 0;
//        sequence = new Integer[8];
        sequence = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0};
        smallField = new Cell[3][3];
        smallField[0][0] = new Cell(new Integer[]{sequence[1], sequence[4], sequence[7]});
//        smallField[0][0] = new Cell(new int[]{sequence[1], sequence[4], sequence[7]});
//        smallField[0][1] = new Cell(new int[]{sequence[1], sequence[0]});
//        smallField[0][2] = new Cell(new int[]{sequence[0], sequence[1], sequence[5]});
//        smallField[1][0] = new Cell(new int[]{sequence[2], sequence[7] });
        smallField[1][1] = new Cell(new Integer[]{sequence[0], sequence[2], sequence[4], sequence[6]});
//        smallField[1][1] = new Cell(new int[]{sequence[0], sequence[2], sequence[4], sequence[6]});
//        smallField[1][2] = new Cell(new int[]{sequence[2], sequence[5]});
//        smallField[2][0] = new Cell(new int[]{sequence[0], sequence[3], sequence[7]});
//        smallField[2][1] = new Cell(new int[]{sequence[3], sequence[6]});
        smallField[2][2] = new Cell(new Integer[]{sequence[3], sequence[4], sequence[5]});
//        smallField[2][2] = new Cell(new int[]{sequence[3], sequence[4], sequence[5]});
    }

    public int getSmallState() {
        return smallState;
    }

    public Integer[] getSequence() {
        return sequence;
    }

    public Cell[][] getSmallField() {
        return smallField;
    }
}
