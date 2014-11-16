package com.example.xander.fappybird;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.view.View;

/**
 * Created by xander on 14-10-29.
 */
abstract class GameObject {

    private PointF position = new PointF(0, 0);
    private int width = 0;
    private int height = 0;
    private boolean gameOver = false;

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public PointF getPosition() {
        return position;
    }

    public void setPosition(PointF position) {
        this.position = position;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public abstract void update(int screenW, int screenH);

    public abstract void draw(Canvas canvas);

    public abstract void touch();

    public abstract void onGameEvent(GameEvent gameEvent);
}
