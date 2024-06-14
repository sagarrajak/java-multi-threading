package com.threadcreation.example.snakegame;

import javax.swing.*;
import java.awt.*;


class SnakeGameMain extends JPanel implements Runnable {
    public static  final int WIDTH = 480;
    public static final int HEIGHT = 480;
    private final int FPS = 30;

    private static final int SQUARE_SIZE = 15;

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, SQUARE_SIZE, SQUARE_SIZE);
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new SnakeGameMain());
        thread.start();
    }

    @Override
    public void run() {
        JFrame frame = new JFrame("Draw Square");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new SnakeGameMain());
        frame.setSize(480, 480);
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.black);
        frame.setVisible(true);
    }
}
