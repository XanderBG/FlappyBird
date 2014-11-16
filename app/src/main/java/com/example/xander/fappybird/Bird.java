package com.example.xander.fappybird;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.SystemClock;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toast;

/**
 * Created by xander on 14-10-29.
 */
public class Bird extends GameObject {
    private Bitmap image;
    private boolean touched = false;
    private long startTouchTime;
    private DecelerateInterpolator interpolator = new DecelerateInterpolator();
    private long finalTime;
    private long birdJumpSize;
    private final long RISE_DELIMITER = 10;
    private final float RISE_VALUE = 5000;
    private CollisionListener collisionListener;
    private RectF birdRect = new RectF();

    public Bird(CollisionListener collisionListener) {
        this.setPosition(new PointF(getPosition().x + 20, getPosition().y));
        this.collisionListener = collisionListener;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    @Override
    public void update(int screenW, int screenH) {
        long currentTime = SystemClock.uptimeMillis();

        if (currentTime < finalTime) {
            float interpolationFloat = ((float)currentTime - (float)startTouchTime) / RISE_VALUE;
            float currentF = interpolator.getInterpolation(interpolationFloat);
            birdJumpSize = screenH / RISE_DELIMITER;
            getPosition().y -= (currentF * birdJumpSize);

        } else {
            getPosition().y += 10;
            if (getPosition().y >= screenH) {
                setGameOver(true);
            }
        }

        birdRect.set(getPosition().x, getPosition().y, getImage().getHeight(), getImage().getWidth());
        collisionListener.checkCollision(birdRect);
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        canvas.drawBitmap(getImage(), getPosition().x, getPosition().y, paint);
    }

    @Override
    public void touch() {
        touched = true;
        startTouchTime = SystemClock.uptimeMillis();
        finalTime = startTouchTime + 500;
    }

    @Override
    public void onGameEvent(GameEvent gameEvent) {

    }
}
