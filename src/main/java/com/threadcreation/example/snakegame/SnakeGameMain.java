package com.threadcreation.example.snakegame;

import com.threadcreation.example.snakegame.game.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class SnakeGameMain extends JPanel implements Runnable {
    public static  final int WIDTH = 480;
    public static final int HEIGHT = 480;

    private static final int SQUARE_SIZE = 15;

    private int x;

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    private int y;

    public SnakeGameMain( int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, SQUARE_SIZE, SQUARE_SIZE);
    }

    public static void main(String[] args) {
//        Thread thread = new Thread(new SnakeGameMain());
//        thread.start();
    }

    @Override
    public void run() {
        JFrame frame = new JFrame("Draw Square");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new SnakeGameMain(0, 0));
        frame.setSize(480, 480);
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.black);
        frame.setVisible(true);
    }
}


class Game implements ActionListener {
    private Timer timer;

    public Game() {
        timer = new Timer(16, this);
        timer.start();
    }

    void update() {

    }

    private void render() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.update();
        this.render();
    }


}
