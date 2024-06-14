package com.threadcreation.example.snakegame.game;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AbstractGameObject implements GameObject {
    List<GameObject> objects = new ArrayList<>();

    @Override
    public void update(int x, int y) {

    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void checkCollision(int x, int y) {

    }
}
