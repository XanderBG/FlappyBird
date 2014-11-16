package com.example.xander.fappybird;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by xander on 14-10-29.
 */
public class DrawingView extends ImageView implements GameClock.GameClockListener {
    private long lastElapsed;
    private ArrayList<GameObject> objects = new ArrayList<GameObject>();

    public ArrayList<GameObject> getObjects() {
        return objects;
    }

    public void addObject(GameObject gameObject) {
        objects.add(gameObject);
    }

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onGameEvent(GameEvent gameEvent) {
        lastElapsed = gameEvent.getCurrentTime();
        this.update();
        this.invalidate();
    }

    public void update() {
        for (GameObject object : getObjects()) {
            if (object.isGameOver()) {
                Toast gameOverToast = Toast.makeText(getContext(), "GAME OVER", Toast.LENGTH_LONG);
                gameOverToast.show();
            } else {
                object.update(getContext().getResources().getDisplayMetrics().widthPixels,
                        getContext().getResources().getDisplayMetrics().heightPixels);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        for (GameObject object : objects) {
            object.touch();
        }
        this.invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (GameObject object : getObjects()) {
            object.draw(canvas);
        }
    }
}
