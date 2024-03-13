package org.example;

public class MapWork {
    private final static char userHit = 'X';
    private final static char botHit = 'O';
    private final static int Size = 3;

    private static char[][] map = new char[Size][Size];
    private static final char emptyPlace = '-';

    public void initialize() {
        for (int i = 0; i < Size; i++) {
            for (int j = 0; j < Size; j++) {
                map[i][j] = emptyPlace;
            }
        }
        System.out.println(map.length);
    }

    public void showMap() {
        System.out.println("   0    1   2");
        for (int i = 0; i < Size; i++) {
            System.out.print(i + "\t");
            for (int j = 0; j < Size; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public int checkSituation(char side) {
        int result = 0;
        int xSit = 0;
        int ySit = 0;
        int diagonal1 = 0;
        int diagonal2 = 0;
        int temp = map.length - 1;
        int nichya = 0;
        int who = 0;
        if (side == userHit)
            who = 1;
        else if (side == botHit)
            who = 2;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] == side) {
                    xSit += 1;
                }
                if (map[j][i] == side) {
                    ySit += 1;
                }
            }

            if (map[i][i] == side) {
                diagonal1 += 1;
            }
            if (map[temp - i][temp - i] == side) {
                diagonal2 += 1;
            }
        }
        if (ySit == map.length) {
            result=who;
        }
        if (xSit == map.length) {
            result=who;
        }
        if (diagonal1== map.length) {
            result=who;
        }
        if (diagonal2 == map.length) {
            result=who;
        }

        if (result == 0) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    if (map[i][j] != emptyPlace) {
                        nichya++;
                        if (nichya == map.length * map[0].length) result = 3;
                    }
                }
            }
        }
        return result;
    }

    public boolean hitMap(int x, int y, int id) {
        if (map[x][y] == emptyPlace) {
            if (id == 0) {
                map[x][y] = userHit;
                return true;
            } else map[x][y] = botHit;
            return true;
        }
        return false;
    }
}

