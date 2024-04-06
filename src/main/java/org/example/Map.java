package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Map extends JPanel {
    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date();
    private static final File GAME_LOG = new File("/Users/Horde/IdeaProjects/MyWork/gameLog.txt");
    private static final Random rnd = new Random();
    private final int human_dot = 1;
    private static final int bot_dot = 2;
    private static final int empty_dot = 0;
    private static final int size = 3;
    private char[][] field;
    private int WIDTH = 1000;
    private int HEIGHT = 500;
    private int cellHEIGHT;
    private static final int DOT_PADDING = 5;
    private int gameOverType;
    private static final int STATE_DRAW = 0;
    private static final int STATE_WIN_HUMAN = 1;
    private static final int STATE_WIN_BOT = 2;
    private static final String MSG_WIN_HUMAN = "Hello world!";
    private static final String MSG_WIN_BOT = "Hellow world!";
    private static final String MSG_NOWIN = "Hello world!";
    private boolean isGameOver;
    private boolean isInit;

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    private int cellWIDTH;


    Map() {
        setBackground(Color.WHITE);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                update(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        isInit = false;
    }

    private void intiMap() {
        field = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field[i][j] = empty_dot;
            }
        }
        writeLog("Новая игра" + "\n Дата сессии: " + df.format(date) + "\n");
    }

    private boolean isValidCell(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }

    private boolean isEmptyCell(int x, int y) {
        return field[x][y] == empty_dot;
    }

    private void aiTurn() {
        int x, y;
        do {
            x = rnd.nextInt(field.length);
            y = rnd.nextInt(field[x].length);
        } while (!isEmptyCell(x, y));
        field[y][x] = bot_dot;

    }

    private boolean checkWin(int c) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

            }
        }
        return false;
    }

    private boolean isMapFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (field[i][j] == empty_dot) return false;
            }
        }
        return true;
    }

    private void update(MouseEvent e) {
        if (isGameOver || !isInit) return;
        int cellX = e.getX() / cellWIDTH;
        int cellY = e.getY() / cellHEIGHT;
        if (!isValidCell(cellX, cellY) || !isEmptyCell(cellX, cellY)) return;
        field[cellX][cellY] = human_dot;

        if (checkEndGame(human_dot, STATE_WIN_HUMAN)) return;
        aiTurn();
        repaint();
        if (checkEndGame(bot_dot, STATE_WIN_BOT)) return;
    }

    private boolean checkEndGame(int dot, int gameOverType) {
        if (checkWin(dot)) {
            this.gameOverType = gameOverType;
            isGameOver = true;
            repaint();
            return true;
        }
        if (isMapFull()) {
            this.gameOverType = STATE_DRAW;
            isGameOver = true;
            repaint();
            return true;
        }
        if (isMapFull()) {
            this.gameOverType = STATE_DRAW;
            repaint();
            return true;
        }
        return false;
    }

    void startNewGame(int mode, int fSzX, int fSzY, int wLen) {
        System.out.printf("Mode: %d;\nSize: x=%d, y=%d;\n Win Length: %d",
                mode, fSzX, fSzY, wLen);
        intiMap();
        isGameOver = false;
        isInit = true;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        render(g);
    }

    private void render(Graphics g) {
        if (!isInit) return;
        int panelWidth = getWIDTH();
        int panelHeight = getHEIGHT();
        cellHEIGHT = panelHeight / 3;
        cellWIDTH = panelWidth / 3;
        g.setColor(Color.BLACK);
        for (int i = 0; i < 3; i++) {
            int y = i * cellHEIGHT;
            g.drawLine(0, y, panelWidth, y);
        }
        for (int i = 0; i < 3; i++) {
            int x = i * cellWIDTH;
            g.drawLine(x, 0, x, panelHeight);
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (field[j][i] == empty_dot) continue;

                if (field[j][i] == human_dot) {
                    g.setColor(Color.BLUE);
                    g.fillOval(i * cellWIDTH + DOT_PADDING, j * cellHEIGHT + DOT_PADDING,
                            cellWIDTH - DOT_PADDING * 2,
                            cellHEIGHT - DOT_PADDING * 2);
                    writeLog("Победил чоловик");


                } else if (field[j][i] == bot_dot) {
                    g.setColor(Color.RED);
                    g.fillOval(i * cellWIDTH + DOT_PADDING, j * cellHEIGHT + DOT_PADDING,
                            cellWIDTH - DOT_PADDING * 2,
                            cellHEIGHT - DOT_PADDING * 2);
                    writeLog("Победил ботик");
                } else {
                    throw new RuntimeException("???????");
                }
            }
        }
        if (isGameOver) {
            writeLog("Победила дружба");
            showMessageGameOver(g);
        }
    }

    private void showMessageGameOver(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, getHEIGHT() - 300, getWIDTH(), 70);
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Times new roman", Font.BOLD, 48));
        switch (gameOverType) {
            case STATE_DRAW:
                g.drawString(MSG_NOWIN, 250, getHEIGHT() / 2);

                break;
            case STATE_WIN_HUMAN:
                g.drawString(MSG_WIN_HUMAN, 250, getHEIGHT() / 2);
                break;
            case STATE_WIN_BOT:
                g.drawString(MSG_WIN_BOT, 250, getHEIGHT() / 2);
                break;
            default:
                throw new RuntimeException("Непонятное состояние игры:" + gameOverType);
        }
    }
    private void writeLog(String msg){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(GAME_LOG, true));
        ) {

            PrintWriter fileWriter = new PrintWriter(bufferedWriter);
            fileWriter.print(msg);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Файл для записи не найден" + e);
        }
    }

}
