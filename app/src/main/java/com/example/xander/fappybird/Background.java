package com.example.xander.fappybird;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;

/**
 * Created by xander on 14-10-29.
 */
public class Background extends GameObject{

    private Bitmap image;

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    @Override
    public void update(int screenW, int screenH) {
        getPosition().x -= 10;
        double absX = Math.abs(getPosition().x);
        if (getImage().getWidth() <= absX) {
            getPosition().x = 0;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();

        canvas.drawBitmap(getImage(), getPosition().x, getPosition().y, paint);
        canvas.drawBitmap(getImage(), getPosition().x + getImage().getWidth(), getPosition().y, paint);
    }

    @Override
    public void touch() {
    }

    @Override
    public void onGameEvent(GameEvent gameEvent) {

    }
}
