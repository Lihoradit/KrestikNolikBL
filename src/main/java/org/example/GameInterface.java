package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameInterface extends JFrame {
    private static final int POSX = 1;
    private static final int POSY = 1;
    private static final int WEIGHT = 1000;
    private static final int HEIGHT = 500;
    private static final String TITLE = "Крестики нолики";
    SettingWindow settings = new SettingWindow(this);
    JButton btnExit = new JButton("Close Game");
    JButton btnStart = new JButton("Start game");
    Map map = new Map();

    GameInterface() {
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings.setVisible(true);
            }
        });
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WEIGHT, HEIGHT);
        setLocation(POSX, POSY);
        setTitle(TITLE);
        JPanel btn = new JPanel();
        btn.add(btnStart);
        btn.add(btnExit);
        add(btn, BorderLayout.SOUTH);
        add(map);
        setResizable(false);
        setVisible(true);

    }
    void settingWindowShow(){

    }

    void startNewGame(int mode, int fSzX, int fSzY, int wLen) {
        map.startNewGame(mode, fSzX, fSzY, wLen);
    }
}
