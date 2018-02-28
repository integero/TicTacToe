package com.ttt.java;

import static javafx.application.Application.launch;

public class Main {
    public static void main(String[] args) {
        launch(Game.class);
        SmField sf = new SmField();
        sf.setXij(0, 0);
        System.out.println(sf.getStateSm());
        sf.setXij(0, 1);
        System.out.println(sf.getStateSm());
        sf.setXij(0, 2);
        System.out.println(sf.getStateSm());
        sf.setOij(1, 0);
        System.out.println(sf.getStateSm());
        sf.setOij(1, 1);
        System.out.println(sf.getStateSm());
        sf.setOij(1, 2);
        System.out.println(sf.getStateSm());
        for (int i = 0; i < sf.getSequence().length; i++) {
            System.out.print(sf.getSequence()[i]+"  ");
        }
        System.out.println();
        Point point=null ;
//        System.out.println(point.bigI);
        new Game();
    }
}
