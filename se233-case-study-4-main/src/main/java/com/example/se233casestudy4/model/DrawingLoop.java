package com.example.se233casestudy4.model;

import com.example.se233casestudy4.view.Platform;

public class DrawingLoop implements Runnable{
    private Platform platform;
    private int frameRate;
    private float interval;
    private boolean running;
    public DrawingLoop(Platform platform) {
        this.platform = platform;
        frameRate = 30;
        interval = 1000.0f / frameRate;
        running = true;
    }
    private void checkDrawCollisions(Characters character) {
        character.checkReachGameWall();
        character.checkReachHighest();
        character.checkReachFloor();
    }
    private void paint(Characters character) {
        character.repaint();
    }
    @Override
    public void run() {
        while (running) {
            float time = System.currentTimeMillis();
            checkDrawCollisions(platform.getCharacter());
            paint(platform.getCharacter());
            time = System.currentTimeMillis() - time;
            if(time < interval) {
                try {
                    Thread.sleep((long) (interval - time));
                } catch (InterruptedException e) {

                }
            }
        }
    }
}
