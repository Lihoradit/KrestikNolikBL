package org.example;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        MapWork map = new MapWork();
        Human human = new Human();
        Bot bot = new Bot();
        map.initialize();
        map.showMap();

        while (true) {
            human.userTurn();
            if (map.checkSituation('X')==1) {
                System.out.println("Поздравляю, соплячок, унизил бота!");
                break;
            } else if (map.checkSituation('X')==3) {
                System.out.println("Ничья!");
                break;
            }
            bot.botTurn();
            System.out.println("___________________");
            map.showMap();
            if (map.checkSituation('O')==2) {
                System.out.println("Тебе не стыдно?");
                break;
            } else if (map.checkSituation('O')==3) {
                System.out.println("Ничья!");
                break;
            }
        }
    }
}
