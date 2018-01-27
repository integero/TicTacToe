public class Cell {
    private int cellState;

    public Cell(int[] seq) {
        this.cellState = 0;
    }

    public int getState() {
        return cellState;
    }

    public boolean setPlus() {
        if (cellState!=0) return false;
         this.cellState = 1;
        return true;
    }
    public boolean setZero() {
        if (cellState!=0) return false;
        this.cellState = -1;
        return true;
    }
}
