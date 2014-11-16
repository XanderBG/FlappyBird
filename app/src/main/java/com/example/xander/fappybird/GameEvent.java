package com.example.xander.fappybird;

/**
 * Created by xander on 14-10-29.
 */
public class GameEvent {
    private long currentTime = 0;

    public GameEvent(long currentTime) {
        this.currentTime = currentTime;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }
}
