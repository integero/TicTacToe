public class Cell {
    private int cellState;
    private Integer[] seq;
//    private int[] seq;

    public Cell(Integer[] seq) {
        this.cellState = 0;
        this.seq = seq;
    }

    public int getState() {
        return cellState;
    }

    public boolean setPlus() {
        if (cellState!=0) return false;
         this.cellState = 1;
        for (int i = 0; i < seq.length; i++) seq[i]++;
        return true;
    }
    public boolean setZero() {
        if (cellState!=0) return false;
        this.cellState = -1;
        for (int i = 0; i < seq.length; i++) seq[i]--;
        return true;
    }
}
