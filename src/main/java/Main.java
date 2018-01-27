public class Main {
    public static void main(String[] args) {
        SmField sf = new SmField();
        sf.setXij(0, 0);
        System.out.println(sf.getSmallState());
        sf.setXij(1, 1);
        System.out.println(sf.getSmallState());
        sf.setXij(2, 2);
        System.out.println(sf.getSmallState());
        sf.setXij(0, 2);
        System.out.println(sf.getSmallState());
        sf.setOij(1, 2);
        System.out.println(sf.getSmallState());
        sf.setOij(1, 2);
        for (int i = 0; i < sf.getSequence().length; i++) {
            System.out.print(sf.getSequence()[i]+"  ");
        }
    }
}
