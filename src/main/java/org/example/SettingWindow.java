package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingWindow extends JFrame {
    private static final int WEIGHT = 200;
    private static final int HEIGHT= 200;
    JButton btnStart = new JButton("New Game");

    SettingWindow(GameInterface gameInterface){
        setLocationRelativeTo(gameInterface);
        setSize(WEIGHT,HEIGHT);
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameInterface.startNewGame(0,3,3,3);
                setVisible(false);
            }
        });
        add(btnStart);
    }
}
