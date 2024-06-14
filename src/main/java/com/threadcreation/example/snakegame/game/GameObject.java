package com.threadcreation.example.snakegame.game;

import java.awt.*;

/// main game loop
public interface GameObject {
    void update(int x, int y);

    void draw(Graphics g);

    void checkCollision(int x, int y);
}

