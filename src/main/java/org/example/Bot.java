package org.example;

import java.util.Random;

public class Bot {
    private static final Random rnd = new Random();
    MapWork map = new MapWork();
    public void botTurn() {
        int botX=rnd.nextInt(0,3);
        int botY= rnd.nextInt(0,3);
        if (!map.hitMap(botX,botY,1)) botTurn();
    }
}
