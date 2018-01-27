public class Sta {
    public final static int[][][] cellSeq = new int[][][]{
            {{1, 4, 7},     {1, 6},         {0, 1, 5}},
            {{2, 7},        {0, 2, 4, 6},   {2, 5}},
            {{0, 3, 7},     {3, 6},         {3, 4, 5}}};

    int[] getSeq(int i, int j) {
        return cellSeq[i][j];
    }
}
