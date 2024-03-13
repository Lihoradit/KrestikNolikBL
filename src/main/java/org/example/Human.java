package org.example;

import java.util.Scanner;

public class Human {
    private static final Scanner scan = new Scanner(System.in);

    static MapWork map = new MapWork();
    public void userTurn() {
        int userInputX;
        int userInputY;
        System.out.print("Введите координату Y:");
        userInputX=Integer.parseInt(scan.nextLine());
        System.out.print("Введите координату X:");
        userInputY=Integer.parseInt(scan.nextLine());
        if(!map.hitMap(userInputX, userInputY, 0)){
            System.out.println("Неккоректная координата, попробуйте еще раз!");
            userTurn();
        }
    }

}
