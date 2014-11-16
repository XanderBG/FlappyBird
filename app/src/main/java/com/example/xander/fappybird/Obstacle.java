package com.example.xander.fappybird;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by xander on 14-11-16.
 */
public class Obstacle extends GameObject implements CollisionListener{

    private Bitmap image;
    private final static int PIPE_GAP = 100;
    private RectF rectBottom = new RectF();
    private RectF rectTop = new RectF();

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    @Override
    public void update(int screenW, int screenH) {
        if (getPosition().x == 0) {
            getPosition().x = screenW;
        } else {
            getPosition().x -= 10;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();

        rectBottom.set(getPosition().x, getPosition().y
                - getImage().getHeight() / 2 - PIPE_GAP, getImage().getHeight(), getImage().getWidth());

        rectTop.set(getPosition().x, getPosition().y
                + getImage().getHeight() / 2 + PIPE_GAP, getImage().getHeight(), getImage().getWidth());

        canvas.drawBitmap(getImage(), rectBottom.left, rectBottom.top, paint);
        canvas.drawBitmap(getImage(), rectTop.left, rectTop.top, paint);
    }

    @Override
    public void touch() {

    }

    @Override
    public void onGameEvent(GameEvent gameEvent) {

    }

    @Override
    public void checkCollision(RectF rectF) {
        if (rectBottom.intersect(rectF)) {
            setGameOver(true);
        }
        if (rectTop.intersect(rectF)) {
            setGameOver(true);
        }
    }
}
