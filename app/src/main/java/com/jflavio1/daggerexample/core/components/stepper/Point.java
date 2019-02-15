package com.jflavio1.daggerexample.core.components.stepper;

import android.graphics.Rect;

public class Point {
    private float x;
    private float y;
    private float radius;
    private Rect rect = new Rect();

    public Point() {
        rect.top = (int) (y-radius);
        rect.bottom = (int) (y+radius);
        rect.left = (int) (x-radius);
        rect.right = (int) (x+radius);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        rect.left = (int) (x - radius);
        rect.right = (int) (x + radius);
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        rect.top = (int) (y - radius);
        rect.bottom = (int) (y + radius);
        this.y = y;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        rect.top = (int) (y - radius);
        rect.bottom = (int) (y + radius);
        rect.left = (int) (x - radius);
        rect.right = (int) (x + radius);
        this.radius = radius;
    }

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }
}
