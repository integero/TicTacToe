public class Main {
    public static void main(String[] args) {
        SmallField sf = new SmallField();
        sf.getSmallField()[0][0].setPlus();
        sf.getSmallField()[1][1].setPlus();
        sf.getSmallField()[2][2].setPlus();
        for (int i = 0; i < sf.getSequence().length; i++) {
            System.out.print(sf.getSequence()[i]+"  ");
        }
    }
}
